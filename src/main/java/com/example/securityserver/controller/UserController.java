package com.example.securityserver.controller;

import com.example.securityserver.apiPayload.ApiResponse;
import com.example.securityserver.converter.UserConverter;
import com.example.securityserver.domain.entity.UserEntity;
import com.example.securityserver.dto.UserRequestDTO;
import com.example.securityserver.dto.UserResponseDTO;
import com.example.securityserver.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final JoinService joinService;

    public UserController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ApiResponse<UserResponseDTO.JoinResultDTO> joinProcess(@RequestBody UserRequestDTO.JoinDTO request){

        System.out.println(request.getUsername());
        UserEntity newUser = joinService.joinProcess(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(newUser));
    }
}
