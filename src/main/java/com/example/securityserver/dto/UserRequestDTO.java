package com.example.securityserver.dto;

import lombok.*;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{
        String username;
        String password;
    }
}