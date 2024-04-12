package com.shop.myshop.api.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    public void test() {
        try{
            Thread.sleep(1000L);
        }catch (Exception e){
        }
        System.out.println("test");
    }

}
