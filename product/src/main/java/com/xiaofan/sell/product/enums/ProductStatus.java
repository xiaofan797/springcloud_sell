package com.xiaofan.sell.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    UP((byte)0,"上架"),
    DOWN((byte)1,"下架"),
    ;
    private Byte code;
    private String message;

    ProductStatus(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
