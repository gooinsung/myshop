package com.shop.myshop.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {

  BEFORE_DELIVERY(0), ON_DELIVERY(1), COMPLETE_DELIVERY(2), CANCEL_DELIVERY(3);

  private final Integer deliveryStatus;
}
