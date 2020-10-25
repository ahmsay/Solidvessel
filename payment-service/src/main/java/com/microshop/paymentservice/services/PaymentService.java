package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.dto.ProductDTO;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    private final IProductService productService;

    public PaymentService(final IPaymentRepository paymentRepository, final IProductService productService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
    }

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Payment> findByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment save(final PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO.getTotalCharge(), paymentDTO.getCustomerId());
        paymentRepository.save(payment);
        productService.setPaymentIds(new ProductDTO(paymentDTO.getProductIds(), payment.getId()));
        return payment;
    }
}
