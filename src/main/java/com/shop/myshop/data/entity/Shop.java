package com.shop.myshop.data.entity;

import com.shop.myshop.data.dto.ShopDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MY_SHOP_USER_SHOP",
        indexes = {
                @Index(name = "idx_USER_SHOP", columnList = "USER_SEQ", unique = true)
        })
public class Shop extends BaseEntity{
  @Id
  @Column(name = "SHOP_SEQ", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long shopSeq;

  @Size(max = 255, message = "샵 이름은 255 자를 넘을 수 없습니다.")
  @Column(name = "SHOP_NAME", nullable = false)
  private String shopName;

  @Column(name = "SHOP_DESCRIPTION")
  private String shopDescription;

  @ManyToOne(optional = false)
  @JoinColumn(name = "USER_SEQ", nullable = false)
  private User user;

  public ShopDto of(){
    return ShopDto
            .builder()
            .shopSeq(this.shopSeq)
            .shopName(this.shopName)
            .shopDescription(this.shopDescription)
            .userSeq(this.user.getUserSeq())
            .build();
  }

}
