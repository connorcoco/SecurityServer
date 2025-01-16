package com.example.securityserver.dto;

import com.example.securityserver.domain.entity.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{

        @NotEmpty
        private String username;

        @NotEmpty
        private String password;

        @NotEmpty
        private String nickname;

        @NotNull
        private Gender gender;
    }
}