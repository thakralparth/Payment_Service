package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class initiatePaymentRequestDto {
    private String orderId;
    private Long amount;
    private String phoneNumber;
    private String email;
}
