package com.example.securityserver.service;

import com.example.securityserver.apiPayload.code.status.ErrorStatus;
import com.example.securityserver.apiPayload.exception.GeneralException;
import com.example.securityserver.jwt.JWTUtil;
import com.example.securityserver.repository.RefreshRepository;
import com.example.securityserver.util.CookieUtil;
import com.example.securityserver.util.RefreshUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshUtil refreshUtil;
    private final RefreshRepository refreshRepository;

    public ReissueService(JWTUtil jwtUtil, RefreshUtil refreshUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshUtil = refreshUtil;
        this.refreshRepository = refreshRepository;
    }

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            throw new GeneralException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            throw new GeneralException(ErrorStatus.REFRESH_TOKEN_EXPIRED);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            throw new GeneralException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }

        // DB에 저장되어 있는지 확인
        Boolean isExistRefresh = refreshRepository.existsByRefresh(refresh);
        if (!isExistRefresh) {

            throw new GeneralException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        refreshUtil.addRefreshEntity(username, newRefresh, 86400000L);

        //response
        response.setHeader("access", newAccess);
        response.addCookie(CookieUtil.createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
