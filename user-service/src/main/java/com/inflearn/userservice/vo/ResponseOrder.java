package com.inflearn.userservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId; //상품번호
    private Integer qty; //수량
    private Integer unitPrice; //단위 가격
    private Integer totalPrice; //총 가격
    private Date createdAt; // 생성일

    private String orderId; // 주문번호
}
