package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Table(name = "MY_SHOP_USER_ROLE_MAP",
        indexes = {
                @Index(name = "idx_USER_ROLE", columnList = "USER_SEQ, ROLE_SEQ", unique = true)
        })
public class UserRole extends BaseEntity {
    @Id
    @Column(name = "USER_ROLE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleSeq;

    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private Role role;
}
