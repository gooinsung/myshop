package com.shop.myshop.api.user.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.myshop.data.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.shop.myshop.data.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

  private final JPAQueryFactory queryFactory;

  public UserDto getUserDtoByIdAndProvider(UserDto userDto){
    return queryFactory
        .select(
            Projections.fields(
                UserDto.class,
                user.userSeq,
                user.userId,
                user.provider,
                user.userPw,
                user.userName,
                user.userNickname,
                user.createdAt,
                user.updatedAt,
                user.isDeleted
            )
    ).from(user)
        .where(userDto.getUserId().isBlank() ? null : user.userId.eq(userDto.getUserId())
            , userDto.getProvider().isBlank() ? null : user.provider.eq(userDto.getProvider())
            , user.isDeleted.eq(Boolean.FALSE)
        ).fetchOne();

  }
}
