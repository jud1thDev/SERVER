package com.efub.leadtoyproject.global.config;
import com.efub.leadtoyproject.global.jwt.JwtAuthenticationFilter;
import com.efub.leadtoyproject.global.jwt.JwtExceptionFilter;
import com.efub.leadtoyproject.global.oauth2.OAuth2FailureHandler;
import com.efub.leadtoyproject.global.oauth2.OAuth2UserService;
import com.efub.leadtoyproject.global.oauth2.OAuth2SuccessHandler;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가
                .requestMatchers("/error", "/favicon.ico");
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedOrigin("http://localhost:5174");
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("https://lead-paladin.vercel.app");
        configuration.addAllowedOrigin("https://api.paladin.n-e.kr");

        configuration.addAllowedMethod("*"); //모든 Method 허용(POST, GET, ...)
        configuration.addAllowedHeader("*"); //모든 Header 허용
        configuration.setAllowCredentials(true); //CORS 요청에서 자격 증명(쿠키, HTTP 헤더) 허용

        configuration.addExposedHeader("Authorization"); // 클라이언트가 특정 헤더값에 접근 가능하도록 하기
        configuration.addExposedHeader("Authorization-Refresh");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration); //위에서 설정한 Configuration 적용
        return source;
    }

    /* 여러 개의 보안 필터를 조합하여 하나의 보안 체인을 생성 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //악의적인 공격 방어를 위해서 CSRF 토큰 사용 비활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) //CORS 설정
                .formLogin(AbstractHttpConfigurer::disable) //폼 기반 로그인을 비활성화->토큰 기반 인증 필요
                .httpBasic(AbstractHttpConfigurer::disable) //HTTP 기본 인증을 비활성화->비밀번호를 평문으로 보내지 않음
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션을 생성하지 않음->토큰 기반 인증 필요
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/oauth2/**").authenticated()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().permitAll()
                )
                // oauth2 설정
                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                        oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                                // 로그인 성공 시 핸들러
                                .successHandler(oAuth2SuccessHandler)
                                // 로그인 실패 시 핸들러
                                .failureHandler(oAuth2FailureHandler)
                )
                // jwt 관련 설정
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), jwtAuthenticationFilter.getClass()) // 토큰 예외 핸들링
//                // 인증 예외 핸들링
//                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                        .accessDeniedHandler(new CustomAccessDeniedHandler()));
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
        ;
        return http.build();
    }
}
