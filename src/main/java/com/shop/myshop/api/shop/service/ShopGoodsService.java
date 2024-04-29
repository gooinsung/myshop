package com.shop.myshop.api.shop.service;

import com.shop.myshop.api.file.dto.FileRequestDto;
import com.shop.myshop.api.file.dto.FileResponseDto;
import com.shop.myshop.api.file.service.FileService;
import com.shop.myshop.api.shop.dto.GoodsRequestDto;
import com.shop.myshop.api.shop.query.ShopGoodsQueryRepository;
import com.shop.myshop.data.dto.ShopGoodsDto;
import com.shop.myshop.data.entity.Shop;
import com.shop.myshop.data.entity.ShopGoods;
import com.shop.myshop.data.repository.ShopGoodsPriceRepository;
import com.shop.myshop.data.repository.ShopGoodsRepository;
import com.shop.myshop.data.repository.ShopRepository;
import com.shop.myshop.exception.CustomExceptionCode;
import com.shop.myshop.exception.custom.BusinessLogicException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ShopGoodsService {
    private final ShopGoodsRepository shopGoodsRepository;
    private final ShopGoodsQueryRepository shopGoodsQueryRepository;
    private final ShopGoodsPriceRepository shopGoodsPriceRepository;
    private final ShopRepository shopRepository;
    private final FileService fileService;

    @Transactional
    public ShopGoodsDto registerGoods(GoodsRequestDto shopGoodsRequestDto) throws IOException {
        ShopGoodsDto shopGoodsDto = ShopGoodsDto
                .builder()
                .shopSeq(shopGoodsRequestDto.getShopSeq())
                .goodsName(shopGoodsRequestDto.getGoodsName())
                .build();

        if (shopGoodsQueryRepository.getShopGoodsDtoByShopAndGoodsName(shopGoodsDto) != null) {
            throw new BusinessLogicException(CustomExceptionCode.DUPLICATED_EXCEPTION);
        }

        FileRequestDto thumbNailRequsetDto = FileRequestDto
                .builder()
                .message("Thumbnail RequestDto")
                .file(shopGoodsRequestDto.getGoodsThumbNail())
                .build();
        FileResponseDto thumbNailResponse = fileService.uploadFile(thumbNailRequsetDto);

        String goodsImgFileUrl = null;
        String goodsImgFileName = null;
        if (shopGoodsRequestDto.getGoodsImg() != null) {
            FileRequestDto goodsImgRequestDto = FileRequestDto
                    .builder()
                    .message("GoodsImg RequsetDto")
                    .file(shopGoodsRequestDto.getGoodsImg())
                    .build();

            FileResponseDto goodsImgResponse = fileService.uploadFile(goodsImgRequestDto);
            goodsImgFileUrl = goodsImgResponse.getFileUrl();
            goodsImgFileName = goodsImgResponse.getOriginalFileName();
        }

        Shop shop = shopRepository.findById(shopGoodsRequestDto.getShopSeq())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 shop 입니다."));

        ShopGoods shopGoods = ShopGoods
                .builder()
                .goodsName(shopGoodsRequestDto.getGoodsName())
                .goodsDescription(shopGoodsRequestDto.getGoodsDescription())
                .goodsThumbnail(thumbNailResponse.getFileUrl())
                .goodsThumbNailFileName(thumbNailResponse.getOriginalFileName())
                .goodsImgUrl(goodsImgFileUrl)
                .goodsImgFileName(goodsImgFileName)
                .shop(shop)
                .build();
        return shopGoodsRepository.saveAndFlush(shopGoods).of();
    }

    public ShopGoodsDto getGoodsDetail(ShopGoodsDto shopGoodsDto){
        ShopGoodsDto userShopGoodsDto = shopGoodsQueryRepository.getShopGoodsDtoByShopSeqAndGoodsSeq(shopGoodsDto);

        if(userShopGoodsDto == null)
            throw new BusinessLogicException(CustomExceptionCode.ENTITY_NOT_FOUND);

        return userShopGoodsDto;
    }
}
