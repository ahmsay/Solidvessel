package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.dto.ProductDTO;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//TODO make all services transactional
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    private final IProductService productService;

    public PaymentService(final IPaymentRepository paymentRepository, final IProductService productService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment addPayment(final PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO.getTotalCharge(), paymentDTO.getCustomerId());
        paymentRepository.save(payment);
        productService.updatePayments(new ProductDTO(paymentDTO.getProductIds(), payment.getId()));
        return payment;
    }
}
