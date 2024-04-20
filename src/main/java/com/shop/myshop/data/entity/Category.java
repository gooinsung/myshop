package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = "MY_SHOP_GOODS_CATEGORY")
public class Category extends BaseEntity {

    @Id
    @Column(name = "CATEGORY_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySeq;

    @Size(max = 255, message = "카테고리 이름은 255 자를 넘을 수 없습니다.")
    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

}
