package com.example.securityserver.controller;

import com.example.securityserver.service.ReissueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "토큰 재발행 API", description = "RefreshToken to AccessToken API")
public class ReissueController {

    private final ReissueService reissueService;


    public ReissueController(ReissueService reissueService){
        this.reissueService = reissueService;
    }

    @Operation(summary = "토큰 재발행", description = "refresh=refreshToken Cookie 요청")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response){
        return reissueService.reissue(request, response);
    }
}
