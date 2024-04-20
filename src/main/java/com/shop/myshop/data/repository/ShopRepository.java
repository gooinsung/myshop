package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.Shop;
import com.shop.myshop.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<List<Shop>> findAllByUser(User user);
}
