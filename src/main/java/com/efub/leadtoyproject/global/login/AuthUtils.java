package com.efub.leadtoyproject.global.login;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class AuthUtils {

    private final MemberRepository memberRepository;

    public Member getMember() {
        return memberRepository.findByEmail(getCurrentMemberEmail()).get();
    }

    public String getCurrentMemberEmail() {
        Object principalObject = getPrincipal();

        if (principalObject instanceof UserDetails) {
            AuthDetails authDetails = (AuthDetails) principalObject;
            log.info("AuthUtils - getMemberId() : 현재 로그인된 Member 객체의 ID를 반환합니다.");
            return authDetails.getUsername();
        }
        return null;
    }

    public Object getPrincipal() {
        log.info("AuthUtils - getPrincipal() 함수 진입");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

}
//    public AuthRole getCurrentUserRole() {
//        log.info("AuthUtils - getCurrentUserRole 함수 진입");
//        Object principalObject = getPrincipal();
//
//        log.info("principal이 UserDetails 인스턴스인지 확인");
//        if (principalObject instanceof UserDetails) {
//            log.info("성공");
//            UserDetails userDetails = (UserDetails) principalObject;
//
//            // UserDetails에서 권한 목록 가져오기
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            GrantedAuthority firstAuthority = authorities.iterator().next();
//            String authorityString = firstAuthority.getAuthority();
//
//            // UserDetails 인스턴스에서 Role String 획득
//            return AuthRole.valueOf(authorityString);
//        }
//        return null;
//    }