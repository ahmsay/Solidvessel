package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private IPaymentRepository paymentRepository;

    public PaymentService(final IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(final long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
