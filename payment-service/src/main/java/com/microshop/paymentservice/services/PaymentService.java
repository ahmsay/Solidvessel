package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;
import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.dto.ProductDTO;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.entity.PaymentProduct;
import com.microshop.paymentservice.event.IEventDispatcher;
import com.microshop.paymentservice.event.PaymentSavedEvent;
import com.microshop.paymentservice.repositories.IPaymentProductRepository;
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
    private final IPaymentProductRepository paymentProductRepository;
    private final IEventDispatcher eventDispatcher;

    public PaymentService(final IPaymentRepository paymentRepository, final IProductService productService,
                          final ICustomerService customerService, final IPaymentProductRepository paymentProductRepository,
                          final IEventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.paymentProductRepository = paymentProductRepository;
        this.eventDispatcher = eventDispatcher;
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
        // TODO Refactor this method
        Payment savedPayment = paymentRepository.save(payment);
        productIds.forEach(productId -> paymentProductRepository.save(new PaymentProduct(savedPayment.getId(), productId)));
        eventDispatcher.send(new PaymentSavedEvent(savedPayment.getId(), savedPayment.getCustomerId()));
        return savedPayment;
    }
}
