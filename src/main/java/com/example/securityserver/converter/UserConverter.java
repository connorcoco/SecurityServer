package com.example.securityserver.converter;

import com.example.securityserver.domain.entity.UserEntity;
import com.example.securityserver.domain.entity.enums.AccountStatus;
import com.example.securityserver.domain.entity.enums.Gender;
import com.example.securityserver.dto.UserRequestDTO;
import com.example.securityserver.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserConverter {

    // 날짜를 포맷하는 메서드
    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(UserEntity user){
        return UserResponseDTO.JoinResultDTO.builder()
                .memberId(user.getId())
                .createAt(formatDateTime(user.getCreatedAt()))
                .build();
    }

    //    UserEntity 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)
    public static UserEntity toUser(UserRequestDTO.JoinDTO request, BCryptPasswordEncoder bCryptPasswordEncoder){

        return UserEntity.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
//                .role("ROLE_USER")
//                .accountStatus(AccountStatus.ACTIVE)
                .nickname(request.getNickname())
                .gender(Gender.valueOf(request.getGender()))
                .build();
    }
}
