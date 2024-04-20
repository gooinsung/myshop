package com.shop.myshop.api.admin.service;

import com.shop.myshop.api.user.query.UserRoleQueryRepository;
import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.enums.MyShopUserRole;
import com.shop.myshop.data.repository.RoleRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import com.shop.myshop.response.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserRoleQueryRepository userRoleQueryRepository;

    @Transactional
    public CustomResponseCode registerAdmin(User user) {

        Role adminRole = roleRepository.findById(MyShopUserRole.ROLE_ADMIN.getRole()).get();

        if (!userRoleQueryRepository.checkUserRole(user, adminRole)) {
            return CustomResponseCode.EXISTS_ENTITY;
        }

        UserRole userAdminRole = UserRole.builder()
                .user(user)
                .role(adminRole)
                .build();

        userRoleRepository.saveAndFlush(userAdminRole);
        return CustomResponseCode.SUCCESS;
    }

}
