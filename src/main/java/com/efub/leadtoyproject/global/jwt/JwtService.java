package com.efub.leadtoyproject.global.jwt;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveOrUpdate(String email, String accessToken) {
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.builder()
                        .email(email)
                        .build());
        member.updateAccessToken(accessToken);
        memberRepository.save(member);
    }
}