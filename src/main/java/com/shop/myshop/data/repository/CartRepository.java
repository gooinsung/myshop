package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.Cart;
import com.shop.myshop.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<List<Cart>> findAllByUser(User user);
}
