package com.isoft.nbawebsite.payment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findAllByUserEmailIgnoreCase(String email);
}
