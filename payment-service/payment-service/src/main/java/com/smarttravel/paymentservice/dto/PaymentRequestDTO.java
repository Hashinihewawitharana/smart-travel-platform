package com.smarttravel.paymentservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    private Long id;
    private Long userId;
    private Long bookingId;
    private Double amount;
    private String paymentMethod;
    private String cardNumber; // optional
    private String status;     // PENDING, COMPLETED, FAILED
}
