package com.example.securityserver.service;

import com.example.securityserver.apiPayload.code.status.ErrorStatus;
import com.example.securityserver.apiPayload.exception.GeneralException;
import com.example.securityserver.converter.UserConverter;
import com.example.securityserver.domain.entity.UserEntity;
import com.example.securityserver.dto.UserRequestDTO;
import com.example.securityserver.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity joinProcess(UserRequestDTO.JoinDTO request){

        Boolean isExist = userRepository.existsByUsername(request.getUsername());

        if(isExist){
            throw new GeneralException(ErrorStatus.MEMBER_IS_EXIST);
        }

        // UserEntity 객체 converter를 통해 생성
        UserEntity newUser = UserConverter.toUser(request, bCryptPasswordEncoder);

        return userRepository.save(newUser);
    }
}
