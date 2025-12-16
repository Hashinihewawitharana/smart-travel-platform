package com.smarttravel.paymentservice.service;


import com.smarttravel.paymentservice.dto.PaymentRequestDTO;

public interface PaymentService {
    PaymentRequestDTO processPayment(PaymentRequestDTO paymentRequestDTO);
}
