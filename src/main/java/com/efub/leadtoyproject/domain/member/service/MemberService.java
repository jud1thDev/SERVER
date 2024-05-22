package com.efub.leadtoyproject.domain.member.service;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import com.efub.leadtoyproject.global.exception.CustomException;
import com.efub.leadtoyproject.global.exception.ErrorCode;
import com.efub.leadtoyproject.global.login.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;

    // (임시) 현재 회원 객체 가져오기
    public Member getCurrentMember() {
        return authUtils.getMember();
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
