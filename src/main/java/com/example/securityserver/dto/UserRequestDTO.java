package com.example.securityserver.dto;

import com.example.securityserver.domain.entity.enums.Gender;
import lombok.*;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{
        private String username;
        private String password;
        private String nickname;
        private Gender gender;
    }
}