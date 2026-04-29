package umc.domain.member.exception;

import umc.domain.member.exception.code.MemberErrorCode;  // ← 추가!

public class MemberException extends RuntimeException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}