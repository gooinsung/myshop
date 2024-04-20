package com.shop.myshop.api.shop.service;

import com.shop.myshop.data.dto.ShopDto;
import com.shop.myshop.data.entity.Shop;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.repository.ShopRepository;
import com.shop.myshop.data.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Transactional
    public ShopDto createShop(ShopDto shopDto) {

        User user = userRepository.findById(shopDto.getUserSeq())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 계정입니다."));

        Shop shop = Shop
                .builder()
                .user(user)
                .shopDescription(shopDto.getShopDescription())
                .shopName(shopDto.getShopName())
                .build();

        Shop createdShop = shopRepository.saveAndFlush(shop);

        return createdShop.of();
    }

}
