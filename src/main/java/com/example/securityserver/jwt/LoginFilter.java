package com.example.securityserver.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/auth/login"); // 원하는 엔드포인트로 변경
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(req);
        String password = obtainPassword(req);

        // 스프링 시큐리티에서 username과 password를 검증하기 위해 token에 담는다.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        // token에 담은 데이터를 검증을 위한 AuthenticationManager로 전달
        // 이 메서드는 CustomUserDetailsService를 통해 사용자의 정보를 조회하고 검증합니다.
        // CustomUserDetailsService와 CustomUserDetails를 통해 DB내의 사용자 정보를 조회하고 입력받은 authToken과 비교를 한다.
        // 인증이 성공적으로 이루어지면, CustomUserDetails를 통해 사용자 정보와 권한을 제공한다.
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("success");
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("failure");
    }

}
