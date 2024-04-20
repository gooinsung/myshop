package com.shop.myshop.api.file.enums;

import lombok.Getter;

@Getter
public enum S3Bucket {
    MSA_MY_SHOP("msamyshop");

    private String bucket;

    S3Bucket(String bucket) {
        this.bucket = bucket;
    }
}
