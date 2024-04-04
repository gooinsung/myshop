package com.shop.myshop.api.admin.service;

import com.shop.myshop.api.user.query.UserRoleQueryRepository;
import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.enums.MyShopUserRole;
import com.shop.myshop.data.repository.RoleRepository;
import com.shop.myshop.data.repository.UserRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserRoleQueryRepository userRoleQueryRepository;

    @Transactional
    public boolean registerAdmin(Long userSeq) {

        User user = userRepository.findById(userSeq).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 User 입니다.");
        });

        Role adminRole = roleRepository.findById(MyShopUserRole.ROLE_ADMIN.getRole()).get();

        System.out.println(userRoleQueryRepository.test(user,adminRole));

        if(userRoleQueryRepository.checkUserRole(user,adminRole)){
            return false;
        }

        UserRole userAdminRole = UserRole.builder()
                .user(user)
                .role(adminRole)
                .build();

        userRoleRepository.saveAndFlush(userAdminRole);
        return true;
    }

}
