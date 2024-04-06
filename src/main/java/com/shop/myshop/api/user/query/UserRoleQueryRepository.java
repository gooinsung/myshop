package com.shop.myshop.api.user.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.myshop.data.dto.UserRoleDto;
import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.shop.myshop.data.entity.QUserRole.userRole;

@Repository
@RequiredArgsConstructor
public class UserRoleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public boolean checkUserRole(User user, Role role) {
        return jpaQueryFactory.
                select(
                        Projections.fields(
                                UserRoleDto.class,
                                userRole.userRoleSeq,
                                userRole.user.userSeq,
                                userRole.role.role
                        )
                ).from(userRole)
                .where(userRole.user.userSeq.eq(user.getUserSeq()),
                        userRole.role.role.eq(role.getRole()))
                .fetchOne() == null;
    }

    public UserRole test(User user, Role role) {
        return jpaQueryFactory.
                select(
                        Projections.fields(
                                UserRole.class,
                                userRole.userRoleSeq
                        )
                ).from(userRole)
                .where(userRole.user.userSeq.eq(user.getUserSeq()),
                        userRole.role.role.eq(role.getRole()))
                .fetchOne();
    }
}
