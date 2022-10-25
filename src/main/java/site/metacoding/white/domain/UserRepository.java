package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IOC 등록
public class UserRepository {

    private final EntityManager em;

    public User save(User user) {
        // Persistance Context에 영속화 시키기 -> 자동 flush(트랜잭션 종료시)
        System.out.println("ccc : " + user.getId());// 영속화전 null
        em.persist(user);// insert 됨(insert쿼리) , persist=영속화
        System.out.println("ccc : " + user.getId());// 영속화가 되서 id를 가진다 = DB와 동기화
        return user;
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
