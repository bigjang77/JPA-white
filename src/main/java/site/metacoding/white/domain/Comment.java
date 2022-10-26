package site.metacoding.white.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Comment {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment, identity를 걸어야 해당 db번호전략을 따라간다
    private Long id;
    private String content;

    // User 누가 썻는지
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // board 어디에 썻는지
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Comment(Long id, String content, User user, Board board) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.board = board;
    }

}
