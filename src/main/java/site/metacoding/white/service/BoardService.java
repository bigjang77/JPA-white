package site.metacoding.white.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;

//서비스의 기능
//트랜젝션관리
//DTO 변환해서 컨트롤러에게 돌려줘야함

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // jpa방식에는 반드시 걸어줘야한다
    public BoardSaveRespDto save(BoardSaveReqDto boardSaveReqDto) {
        // 핵심로직
        Board boardPS = boardRepository.save(boardSaveReqDto.toEntity());

        // DTO전환
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(boardPS);

        return boardSaveRespDto;
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        Board boardPS = boardRepository.findById(id);
        boardPS.getUser().getUsername();// Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번 날라감)
        // 4. user select 됨?
        System.out.println("서비스단에서 지연로딩 함. 왜? 여기까지는 디비커넥션이 유지되니까");
        return boardPS;
    }

    @Transactional
    public void update(Long id, Board board) {
        Board boardPS = boardRepository.findById(id);// 영속화 된 데이터를 수정한다
        boardPS.update(board.getTitle(), board.getContent());
    }// 트렌직션 종료시 ->더티체킹을 함

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
