package com.efub.leadtoyproject.member.repository;

import com.efub.leadtoyproject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
