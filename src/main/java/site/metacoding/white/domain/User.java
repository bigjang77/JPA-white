package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity // =VO밸류오브젝트, Dto와 차이점=변경불가능하다
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment, identity를 걸어야 해당 db번호전략을 따라간다
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @Builder
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
