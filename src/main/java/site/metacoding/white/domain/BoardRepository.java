package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);// insert 됨(insert쿼리) , persist=영속화
    }

    // JPQL 문법
    public Board findById(Long id) {
        Board boardPS = em.createQuery("select b from Board b where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
        return boardPS;
    }
}
