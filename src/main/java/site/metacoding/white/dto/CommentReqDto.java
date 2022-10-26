package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;

public class CommentReqDto {

    @Setter
    @Getter
    public static class CommentSaveReqDto {
        private String content;
        private SessionUser sessionuser;// 서비스로직
        // 보드도 필요한데?
        private Long boardId;

        public Comment toEntity(Board board) {
            return Comment.builder().content(content).board(board).user(sessionuser.toEntity()).build();
        }
    }
}
