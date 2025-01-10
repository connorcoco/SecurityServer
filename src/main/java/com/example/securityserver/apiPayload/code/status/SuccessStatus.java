package com.example.securityserver.apiPayload.code.status;

import com.example.securityserver.apiPayload.code.BaseResponseCode;
import com.example.securityserver.apiPayload.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseResponseCode {

    // 가장 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDTO getReason() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(this == _OK)
                .build();
    }

    @Override
    public ResponseDTO getReasonHttpStatus() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(this == _OK)
                .httpStatus(httpStatus)
                .build();
    }
}