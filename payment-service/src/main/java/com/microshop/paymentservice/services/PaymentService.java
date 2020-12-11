package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.entity.Customer;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;
    private final IProductService productService;
    private final ICustomerService customerService;

    public PaymentService(final IPaymentRepository paymentRepository, final IProductService productService, final ICustomerService customerService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentDTO findById(final Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            return null;
        }
        Customer customer = customerService.findById(payment.getCustomerId());
        return new PaymentDTO(payment.getId(), payment.getTotalCharge(), customer);
    }

    @Override
    public Iterable<Payment> findByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment save(final Payment payment, final List<Long> productIds) {
        paymentRepository.save(payment);
        productService.setPaymentIds(payment.getId(), productIds);
        return payment;
    }
}