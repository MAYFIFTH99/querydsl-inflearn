package jpa.querydsl.repository;

import jakarta.persistence.EntityManager;
import jpa.querydsl.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class MemberJPARepositoryTest {

//    @Autowired
//    EntityManager em;

    @Autowired
    MemberJPARepository memberJPARepository;

    @Test
    @DisplayName("save")
    void save() throws Exception {
        //given
        Member member = new Member();
        //when
        memberJPARepository.save(member);
        //then
        assertThat(member.getId()).isNotNull();
    }

    @Test
    @DisplayName("findById")
    void findById() throws Exception {
        //given
        Member member = new Member();
        memberJPARepository.save(member);
        //when
        Member findMember = memberJPARepository.findById(member.getId()).get();
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
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);
        memberJPARepository.save(member3);
        //when
        List<Member> memberList = memberJPARepository.findAll();
        //then
        assertThat(memberList).hasSize(3);
    }

    @Test
    void findByUsernameQuerydsl() throws Exception {
        //given
        Member member = new Member("hello");
        memberJPARepository.save(member);
        //when
        List<Member> findMember = memberJPARepository.findByUsername_Querydsl("hello");
        //then
        assertThat(findMember).hasSize(1);
        assertThat(findMember.get(0)).isEqualTo(member);
    }

    @Test
    void findAllQuerydsl() throws Exception {
        //given
        List<Member> members = new ArrayList<>();
        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();

        members.add(member1);
        members.add(member2);
        members.add(member3);
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);
        memberJPARepository.save(member3);
        //when
        List<Member> memberList = memberJPARepository.findAll_Querydsl();
        //then
        assertThat(memberList).hasSize(3);
    }
}