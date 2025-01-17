package com.example.securityserver.dto;

import com.example.securityserver.domain.entity.enums.Gender;
import com.example.securityserver.validation.annotation.GenderValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{

        @NotEmpty
        @Email(message = "Invalid email format")
        private String username;

        @NotEmpty
        private String password;

        @NotEmpty
        private String nickname;

        @NotNull
        @GenderValid
        private String gender;
    }
}