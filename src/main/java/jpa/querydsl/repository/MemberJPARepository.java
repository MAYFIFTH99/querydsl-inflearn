package jpa.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jpa.querydsl.entity.Member;
import jpa.querydsl.entity.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static jpa.querydsl.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberJPARepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

//    public MemberJPARepository(EntityManager em, JPAQueryFactory queryFactory) {
//        this.em = em;
//        this.queryFactory = queryFactory;
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findAll_Querydsl() {
        return queryFactory
                .select(member)
                .from(member)
                .fetch();
    }

    public Optional<List<Member>> findByUsername(String username) {
        return Optional.ofNullable(
                em.createQuery("select m from Member m where m.username =:username", Member.class)
                        .setParameter("username", username)
                        .getResultList()
        );
    }

    public List<Member> findByUsername_Querydsl(String username) {
        return queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq(username))
                .fetch();
    }


}
