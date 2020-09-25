package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        payments = new ArrayList<>();
        payments.add(new Payment("1", 10.5, "1"));
        payments.add(new Payment("2", 200, "2"));
        payments.add(new Payment("3", 999.99, "2"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment getPaymentById(final String id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
