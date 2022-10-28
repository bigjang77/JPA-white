package site.metacoding.white.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Board {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment, identity를 걸어야 해당 db번호전략을 따라간다
    private Long id;
    private String title;
    @Column(length = 1000)
    private String content;

    // FK가 만들어짐 user_id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 조회를 위해서만 필요함 컬럼이 아니라고 설정해아함 기본전략이 lazy=들고올것이많아서
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    // 테스트할때 보드
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Builder
    public Board(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 변경하는 코드는 의미 있게 메서드로 구현
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}