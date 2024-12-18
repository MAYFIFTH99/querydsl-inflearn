package jpa.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jpa.querydsl.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJPARepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJPARepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<List<Member>> findByUsername(String username) {
        return Optional.ofNullable(
                em.createQuery("select m from Member m where m.username =:username", Member.class)
                        .setParameter("username", username)
                        .getResultList()
        );
    }


}
