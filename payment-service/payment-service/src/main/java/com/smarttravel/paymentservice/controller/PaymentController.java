package com.smarttravel.paymentservice.controller;


import com.smarttravel.paymentservice.dto.PaymentRequestDTO;
import com.smarttravel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentRequestDTO> processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentRequestDTO response = paymentService.processPayment(paymentRequestDTO);
        return ResponseEntity.ok(response);
    }
}
