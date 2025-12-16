package com.smarttravel.paymentservice.service;
import com.smarttravel.paymentservice.dto.PaymentRequestDTO;
import com.smarttravel.paymentservice.entity.Payment;
import com.smarttravel.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentRequestDTO processPayment(PaymentRequestDTO dto) {
        Payment payment = new Payment(
                null,
                dto.getUserId(),
                dto.getBookingId(),
                dto.getAmount(),
                dto.getPaymentMethod(),
                "COMPLETED" // simulate success
        );

        Payment saved = paymentRepository.save(payment);

        dto.setId(saved.getId());
        dto.setStatus(saved.getStatus());
        return dto;
    }
}
