package com.shop.myshop.response;

import lombok.Getter;

@Getter
public enum CustomResponseCode {

    SUCCESS("000","요청 성공"),
    FAIL("200", "요청 실패"),
    
    
    EXISTS_ENTITY("001","이미 존재하는 엔티티 입니다."),
    ;

    private String code;
    private String message;

    CustomResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
