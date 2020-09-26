package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private List<Payment> payments;

    public PaymentRepository() {
        payments = new ArrayList<>();
        payments.add(new Payment(1L, 10.5, 1L, 1L));
        payments.add(new Payment(2L, 200, 2L, 2L));
        payments.add(new Payment(3L, 999.99, 2L, 3L));
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment getPaymentById(final long id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final long customerId) {
        return payments.stream()
                .filter(payment -> payment.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
}
