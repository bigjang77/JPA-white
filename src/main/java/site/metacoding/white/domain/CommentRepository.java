package site.metacoding.white.domain;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IOC 등록
public class CommentRepository {

    private final EntityManager em;

    public Comment save(Comment comment) {
        // Persistance Context에 영속화 시키기 -> 자동 flush(트랜잭션 종료시)
        em.persist(comment);// insert 됨(insert쿼리) , persist=영속화
        return comment;
    }

    public void deleteById(Long id) {
        em.createQuery("delete from Comment c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Optional<Comment> findById(Long id) {
        try {
            Optional<Comment> commentOP = Optional.of(em
                    .createQuery("select c from Comment c where c.id = :id",
                            Comment.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return commentOP;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
