package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_DELIVERY")
public class Delivery extends BaseEntity {

    @Id
    @Column(name = "DELIVERY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliverySeq;

    @Size(max = 255, message = "배송기사 이름은 255 자를 넘을 수 없습니다.")
    @Column(name = "DELIVER_NAME", nullable = false)
    private String deliverName;

    @ColumnDefault("0")
    @Column(name = "DELIVERY_STATUS", nullable = false)
    private Integer deliveryStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_DETAIL_SEQ", nullable = false)
    private OrderDetail orderDetail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_SEQ", nullable = false)
    private Order order;

}
