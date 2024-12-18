package jpa.querydsl.repository;

import jpa.querydsl.dto.MemberSearchCond;
import jpa.querydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCond cond);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCond cond, Pageable pageable);
    Page<MemberTeamDto> searchSimpleComplex(MemberSearchCond cond, Pageable pageable);
}
