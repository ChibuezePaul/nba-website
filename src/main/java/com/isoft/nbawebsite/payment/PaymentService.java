package com.isoft.nbawebsite.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class PaymentService{

    private final PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getUserPayments(String email) {
        return paymentRepository.findAllByUserEmailIgnoreCase(email);
    }
}