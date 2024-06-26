package com.shop.myshop.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    BEFORE_ORDER(0), ORDER_COMPLETE(1), ORDER_CANCEL(9);

    private final Integer orderStatus;
}
