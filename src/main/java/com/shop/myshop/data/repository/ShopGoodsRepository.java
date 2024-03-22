package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.ShopGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGoodsRepository extends JpaRepository<ShopGoods, Long> {

}
