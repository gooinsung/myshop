package com.shop.myshop.data.repository;

import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<List<UserRole>> findAllByUser(User user);
}
