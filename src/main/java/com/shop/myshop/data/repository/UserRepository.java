package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  Optional<User> findByUserId(String userId);
  Optional<User> findByUserIdAndProvider(String userId, String provider);

}
