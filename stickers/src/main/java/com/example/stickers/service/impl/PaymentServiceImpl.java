package com.example.stickers.service.impl;


import com.example.stickers.dto.PaymentIntentRequestDto;
import com.example.stickers.dto.PaymentIntentResponseDto;
import com.example.stickers.service.IPaymentService;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Override
    public PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto) {

        try{
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(requestDto.amount())
                .setCurrency(requestDto.currency())
                .addPaymentMethodType("card").build();


            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentResponseDto(paymentIntent.getClientSecret());

        }catch (Exception e){
            throw new RuntimeException("Failed to create PaymentIntent", e);
        }



    }
}
