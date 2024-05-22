package com.efub.leadtoyproject.global.login;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Component
public class AuthUtils {

    private final MemberRepository memberRepository;

    public Member getMember() {
        String email = getCurrentMemberEmail();
        if (email != null) {
            log.info("인증 정보의 이메일: " + email);
            Optional<Member> memberOptional = memberRepository.findByEmail(email);
            if (memberOptional.isPresent()) {
                return memberOptional.get();
            } else {
                log.error("해당 이메일로 회원을 찾을 수 없습니다: {}", email);
                throw new NoSuchElementException("제공된 이메일로 회원을 찾을 수 없습니다");
            }
        } else {
            log.info("인증 정보에 이메일이 없습니다.");
            log.error("현재 회원의 이메일을 찾을 수 없습니다");
            throw new NoSuchElementException("현재 회원의 이메일을 찾을 수 없습니다");
        }
    }

    public String getCurrentMemberEmail() {
        Object principalObject = getPrincipal();

        if (principalObject instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principalObject;
            log.info("AuthUtils - getCurrentMemberEmail() : 현재 로그인된 회원의 이메일을 반환합니다.");
            return userDetails.getUsername();
        } else {
            log.error("Principal 객체가 UserDetails의 인스턴스가 아닙니다");
            return null;
        }
    }

    public Object getPrincipal() {
        log.info("AuthUtils - getPrincipal() 메서드 진입");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getPrincipal();
        } else {
            log.error("SecurityContext에 authentication이 없습니다.");
            return null;
        }
    }
}