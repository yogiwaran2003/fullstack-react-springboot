package com.example.stickers.service;

import com.example.stickers.dto.PaymentIntentRequestDto;
import com.example.stickers.dto.PaymentIntentResponseDto;


public interface IPaymentService {

    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto);
}
