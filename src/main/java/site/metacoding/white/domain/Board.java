package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
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
}