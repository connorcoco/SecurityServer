package com.example.securityserver.apiPayload.exception;

import com.example.securityserver.apiPayload.code.BaseCode;
import com.example.securityserver.apiPayload.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseCode code;

    public ResponseDTO getErrorReason() {
        return this.code.getReason();
    }

    public ResponseDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}