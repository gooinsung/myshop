package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.ShopGoodsPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGoodsPriceRepository extends JpaRepository<ShopGoodsPrice, Long> {

}
