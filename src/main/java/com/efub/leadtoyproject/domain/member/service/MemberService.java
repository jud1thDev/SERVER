package com.efub.leadtoyproject.domain.member.service;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import com.efub.leadtoyproject.global.exception.CustomException;
import com.efub.leadtoyproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // (임시) 현재 회원 객체 가져오기
    public Member getCurrentMember() {
        Member member = Member.builder()
                .memberId(1L)
                .email("testuser@gmail.com")
                .accessToken("TestAccessToken")
                .refreshToken("TestRefreshToken")
                .build();
        return member;
        // return valMember(member.getMemberId()); - TODO: 추후 Auth 개발시 이 부분 로직 변경 예정
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
