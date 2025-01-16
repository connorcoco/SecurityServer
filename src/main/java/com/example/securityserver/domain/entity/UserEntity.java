package com.example.securityserver.domain.entity;

import com.example.securityserver.domain.common.BaseEntity;
import com.example.securityserver.domain.entity.enums.AccountStatus;
import com.example.securityserver.domain.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 실제로는 email값이 들어감
    private String password;
    private String role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
