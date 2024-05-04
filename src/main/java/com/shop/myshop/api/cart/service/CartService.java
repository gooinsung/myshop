package com.shop.myshop.api.cart.service;

import com.shop.myshop.api.cart.query.CartQueryRepository;
import com.shop.myshop.data.dto.CartDto;
import com.shop.myshop.data.entity.Cart;
import com.shop.myshop.data.entity.ShopGoods;
import com.shop.myshop.data.entity.ShopGoodsPrice;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.repository.CartRepository;
import com.shop.myshop.data.repository.ShopGoodsPriceRepository;
import com.shop.myshop.data.repository.ShopGoodsRepository;
import com.shop.myshop.data.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartQueryRepository cartQueryRepository;
    private final UserRepository userRepository;
    private final ShopGoodsRepository shopGoodsRepository;
    private final ShopGoodsPriceRepository shopGoodsPriceRepository;


    public List<CartDto> getUserCartList(User user) {
        return cartQueryRepository.findAllByUserSeq(user.getUserSeq());
    }

    @Transactional
    public Boolean registerCart(CartDto cartDto) {
        CartDto userCartDto = cartQueryRepository.findByUserSeqAndGoodsSeq(cartDto);

        if (userCartDto == null) {
            User user = userRepository.findById(cartDto.getUserSeq())
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계정입니다."));

            ShopGoods shopGoods = shopGoodsRepository.findById(cartDto.getGoodsSeq())
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 상품입니다."));

            ShopGoodsPrice shopGoodsPrice = shopGoodsPriceRepository.findById(cartDto.getGoodsPriceSeq())
                    .orElseThrow(() -> new EntityNotFoundException("등록되지 않는 상품의 가격입니다."));

            Cart cart = Cart
                    .builder()
                    .user(user)
                    .shopGoods(shopGoods)
                    .shopGoodsPrice(shopGoodsPrice)
                    .build();

            cartRepository.saveAndFlush(cart);
        } else {
            Cart cart = cartRepository.findById(userCartDto.getCartSeq()).get();

            ShopGoodsPrice shopGoodsPrice = shopGoodsPriceRepository.findById(cartDto.getGoodsPriceSeq())
                    .orElseThrow(() -> new EntityNotFoundException("등록되지 않는 상품의 가격입니다."));

            cart.updatePrice(shopGoodsPrice);
        }

        return true;
    }

    @Transactional
    public void deleteCart(CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getCartSeq())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 엔티티 입니다."));

        if (!Objects.equals(cart.getUser().getUserSeq(), cartDto.getUserSeq())) {
            throw new IllegalArgumentException();
        }

        cartRepository.delete(cart);
    }
}
