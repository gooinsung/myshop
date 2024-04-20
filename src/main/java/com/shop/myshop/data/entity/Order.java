package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_ORDER")
public class Order extends BaseEntity {

    @Id
    @Column(name = "ORDER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSeq;

    @Column(name = "ORDER_TOTAL_PRICE", nullable = false)
    private BigDecimal orderTotalPrice;

    @ColumnDefault("0")
    @Column(name = "ORDER_STATUS", nullable = false)
    private Integer orderStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SHOP_SEQ", nullable = false)
    private Shop shop;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_SEQ", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList;

}
