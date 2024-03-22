package com.shop.myshop.data.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MyshopUserRole {

  ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

  private final String role;
}
