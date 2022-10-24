package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // =VO밸류오브젝트, Dto와 차이점=변경불가능하다
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment, identity를 걸어야 해당 db번호전략을 따라간다
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
}
