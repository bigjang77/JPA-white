package site.metacoding.white.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.LoginReqDto;
import site.metacoding.white.dto.UserReqDto.UserUpdateReqDto;
import site.metacoding.white.dto.UserRespDto.JoinRespDto;
import site.metacoding.white.dto.UserRespDto.UserAllRespDto;
import site.metacoding.white.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // 회원탈퇴
    @DeleteMapping("/user{id}")
    public ResponseDto<?> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseDto<>(1, "성공", null);
    }

    // 회원정보 수정
    @PutMapping("/user/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody UserUpdateReqDto userUpdateReqDto) {
        userUpdateReqDto.setId(id);
        return new ResponseDto<>(1, "성공", userService.update(userUpdateReqDto));
    }

    // 회원정보 한건보기
    @GetMapping("/user/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        return new ResponseDto<>(1, "성공", userService.findById(id));
    }

    // 회원정보 모두보기
    @GetMapping("/user")
    public List<UserAllRespDto> findAll() {
        return userService.findAll();
    }

    // joinDto 만들어서 실습해보기
    @PostMapping("/join")
    public ResponseDto<?> save(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.save(joinReqDto);
        return new ResponseDto<>(1, "ok", joinRespDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginReqDto loginReqDto) {
        SessionUser sessionUser = userService.login(loginReqDto);
        session.setAttribute("sessionUser", sessionUser);
        return new ResponseDto<>(1, "ok", sessionUser);
    }
}
