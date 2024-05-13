package com.efub.leadtoyproject.domain.member.service;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long getCurrentMemberId() {
        return 1L;
    }

    public Member getCurrentMember() {
        return memberRepository.findByMemberId(getCurrentMemberId())
                .orElseThrow();
    }
}
