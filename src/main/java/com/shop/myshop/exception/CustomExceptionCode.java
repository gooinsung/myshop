package com.shop.myshop.exception;

import lombok.Getter;

@Getter
public enum CustomExceptionCode {

    HTTP_METHOD_NOT_SUPPORTED("H001", "지원하지 않는 Http Method 입니다."),
    NOT_FOUND("H002", "존재하지 않는 도메인 입니다."),
    UNAUTHORIZED("H401", "인증되지 않은 접근입니다."),
    FORBIDDEN("H403", "해당 접근에 대한 권한이 없습니다."),
    AUTHENTICATION_FAILED("H005", "인증에 실패하였습니다."),


    // User 도메인
    EXISTED_USER_ID("U001", "이미 존재하는 아이디 입니다."),
    WRONG_PW("U002", "비밀번호를 확인해 주세요."),


    // JPA 관련
    ENTITY_NOT_FOUND("E001", "존재하지 않는 엔티티 입니다."),

    BUSINESS_LOGIC_EXCEPTION("ER001", "비즈니스 로직 실행 중 오류가 발생하였습니다."),
    RUNTIME_EXCEPTION("ER002", "예상치 못한 런타임 예외가 발생하였습니다."),
    VALIDATION_PARAMETER_EXCEPTION("ER003", "유효하지않은 파라미터입니다."),
    IOEXCEPTION("ER004", "이미지 등록 시 예외가 발생하였습니다."),
    DUPLICATED_EXCEPTION("ER005", "중복되는 데이터 입니다."),


    ;
    private final String code;
    private final String messgae;

    CustomExceptionCode(String code, String messgae) {
        this.code = code;
        this.messgae = messgae;
    }
}
