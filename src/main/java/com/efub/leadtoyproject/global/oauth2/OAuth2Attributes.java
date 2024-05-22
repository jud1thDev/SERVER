package com.efub.leadtoyproject.global.oauth2;

import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.global.exception.CustomException;
import com.efub.leadtoyproject.global.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {
    private String oauth2UserEmail;

    @Builder
    private OAuth2Attributes(String oauth2UserEmail) {
        this.oauth2UserEmail = oauth2UserEmail;
    }
    
    public static OAuth2Attributes ofKakao(String registrationId, Map<String, Object> attributes) {
        if (!registrationId.equals("kakao"))
            throw new CustomException(ErrorCode.ILLEGAL_REGISTRATION_ID);
        Map<String, Object> accountAttributes = (Map<String, Object>) attributes.get("kakao_account");

        return OAuth2Attributes.builder()
                .oauth2UserEmail(String.valueOf(accountAttributes.get("email")))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .email(oauth2UserEmail)
                .build();
    }
}
