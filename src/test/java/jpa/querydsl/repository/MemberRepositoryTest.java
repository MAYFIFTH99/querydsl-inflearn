package jpa.querydsl.repository;

import jakarta.persistence.EntityManager;
import jpa.querydsl.dto.MemberSearchCond;
import jpa.querydsl.dto.MemberTeamDto;
import jpa.querydsl.entity.Member;
import jpa.querydsl.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("save")
    void save() throws Exception {
        //given
        Member member = new Member();
        //when
        memberRepository.save(member);
        //then
        assertThat(member.getId()).isNotNull();
    }

    @Test
    @DisplayName("findById")
    void findById() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        //when
        Member findMember = memberRepository.findById(member.getId()).get();
        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void findAll() throws Exception {
        //given
        List<Member> members = new ArrayList<>();
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();

        members.add(member1);
        members.add(member2);
        members.add(member3);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        //when
        List<Member> memberList = memberRepository.findAll();
        //then
        assertThat(memberList).hasSize(3);
    }

    @Test
    void findByUsernameQuerydsl() throws Exception {
        //given
        Member member = new Member("hello");
        memberRepository.save(member);
        //when
        List<Member> findMember = memberRepository.findByUsername("hello");
        //then
        assertThat(findMember).hasSize(1);
        assertThat(findMember.get(0)).isEqualTo(member);
    }
    @Test
    void search() throws Exception {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCond cond = new MemberSearchCond();
        cond.setAgeGoe(20);

        List<MemberTeamDto> results = memberRepository.search(cond);

        assertThat(results).hasSize(3);
    }



}