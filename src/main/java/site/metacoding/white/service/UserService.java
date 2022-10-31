package site.metacoding.white.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.LoginReqDto;
import site.metacoding.white.dto.UserReqDto.UserUpdateReqDto;
import site.metacoding.white.dto.UserRespDto.JoinRespDto;
import site.metacoding.white.dto.UserRespDto.UserAllRespDto;
import site.metacoding.white.dto.UserRespDto.UserDetailRespDto;
import site.metacoding.white.dto.UserRespDto.UserUpdateRespDto;
import site.metacoding.white.util.SHA256;

//서비스의 기능
//트랜젝션관리
//DTO 변환해서 컨트롤러에게 돌려줘야함

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SHA256 sha256;

    // 응답의 DTO는 서비스에서 만든다
    @Transactional // 트랜잭션을 붙이지 않으면 영속화 되어있는 객체가 flush가 안됨.
    public JoinRespDto save(JoinReqDto joinReqDto) {
        // 비밀번호 해시
        String encPassword = sha256.encrypt(joinReqDto.getPassword());
        joinReqDto.setPassword(encPassword);

        // 회원정보 저장
        User userPS = userRepository.save(joinReqDto.toEntity());
        // DTO 리턴
        return new JoinRespDto(userPS);
    }

    // 유저서비스
    @Transactional(readOnly = true)
    public SessionUser login(LoginReqDto loginReqDto) {
        String encPassword = sha256.encrypt(loginReqDto.getPassword());
        Optional<User> userOP = userRepository.findByUsername(loginReqDto.getUsername());
        if (userOP.isEmpty()) {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }

        User userPS = userOP.get();
        if (!userPS.getPassword().equals(encPassword)) {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }

        return new SessionUser(userPS);
    } // 트랜잭션 종료

    @Transactional(readOnly = true)
    public List<UserAllRespDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserAllRespDto> userAllRespDtoList = new ArrayList<>();
        // List의 크기만큼 for문 돌리기
        for (User user : userList) {
            userAllRespDtoList.add(new UserAllRespDto(user));
        }
        return userAllRespDtoList;
    }

    @Transactional(readOnly = true)
    public UserDetailRespDto findById(Long id) {
        Optional<User> userOP = userRepository.findById(id);
        if (userOP.isPresent()) {
            UserDetailRespDto userDetailRespDto = new UserDetailRespDto(userOP.get());
            return userDetailRespDto;
        } else {
            throw new RuntimeException("해당" + id + "로 상세보기를 할 수 없습니다.");
        }
    }

    @Transactional
    public UserUpdateRespDto update(UserUpdateReqDto userUpdateReqDto) {
        Long id = userUpdateReqDto.getId();
        Optional<User> userOP = userRepository.findById(id);// 영속화 된 데이터를 수정한다
        if (userOP.isPresent()) {
            User userPS = userOP.get();
            userPS.update(userUpdateReqDto.getUsername(), userUpdateReqDto.getPassword());
            return new UserUpdateRespDto(userPS);
        } else {
            throw new RuntimeException("해당" + id + "로 수정을 할 수 없습니다.");
        }
    }

    public void deleteById(Long id) {

    }// 트렌직션 종료시 ->더티체킹을 함
}
