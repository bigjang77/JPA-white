package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IOC 등록
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        // Persistance Context에 영속화 시키기 -> 자동 flush(트랜잭션 종료시)
        em.persist(user);// insert 됨(insert쿼리) , persist=영속화
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
