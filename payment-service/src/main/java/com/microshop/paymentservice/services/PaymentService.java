package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.repositories.IPaymentRepository;
import com.microshop.paymentservice.wrapper.CustomerDTO;
import com.microshop.paymentservice.wrapper.PaymentDTO;
import com.microshop.paymentservice.wrapper.ProductDTO;
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
        Payment payment = findPrunedById(id);
        if (payment == null) {
            return null;
        }
        CustomerDTO customer = customerService.findById(payment.getCustomerId());
        List<ProductDTO> productList = productService.findByPaymentId(payment.getId());
        return new PaymentDTO(payment.getId(), payment.getTotalCharge(), customer, productList);
    }

    @Override
    public Payment findPrunedById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
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
