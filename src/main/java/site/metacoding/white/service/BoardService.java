package site.metacoding.white.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardReqDto.BoardUpdateReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardAllRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardUpdateRespDto;

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
    public BoardDetailRespDto findById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardOP.get());
            return boardDetailRespDto;
        } else {
            throw new RuntimeException("해당" + id + "로 상세보기를 할 수 없습니다.");
        }
    }

    @Transactional
    public BoardUpdateRespDto update(BoardUpdateReqDto boardUpdateReqDto) {
        Long id = boardUpdateReqDto.getId();
        Optional<Board> boardOP = boardRepository.findById(id);// 영속화 된 데이터를 수정한다
        if (boardOP.isPresent()) {
            Board boardPS = boardOP.get();
            boardPS.update(boardUpdateReqDto.getTitle(), boardUpdateReqDto.getContent());
            return new BoardUpdateRespDto(boardPS);
        } else {
            throw new RuntimeException("해당" + id + "로 수정을 할 수 없습니다.");
        }
    }// 트렌직션 종료시 ->더티체킹을 함

    @Transactional(readOnly = true)
    public List<BoardAllRespDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardAllRespDto> boardAllRespDtoList = new ArrayList<>();
        // List의 크기만큼 for문 돌리기
        for (Board board : boardList) {
            boardAllRespDtoList.add(new BoardAllRespDto(board));
        }
        return boardAllRespDtoList;
    }

    // delete는 리턴안함
    @Transactional
    public void deleteById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new RuntimeException("해당 " + id + "로 삭제를 할 수 없습니다.");
        }
    }
}
