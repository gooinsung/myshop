package com.shop.myshop.data.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleDto {
    private Long userRoleSeq;
    private Long userSeq;
    private String role;
}
