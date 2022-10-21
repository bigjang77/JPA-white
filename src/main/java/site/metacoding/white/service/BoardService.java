package site.metacoding.white.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // jpa방식에는 반드시 걸어줘야한다
    public void save(Board board) {// 필요업지만 관리상 만들어줘야한다
        boardRepository.save(board);
    }
}
