package com.microshop.paymentservice.service;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.event.EventDispatcher;
import com.microshop.paymentservice.event.PaymentSavedEvent;
import com.microshop.paymentservice.repository.PaymentRepository;
import com.microshop.paymentservice.request.SavePaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SaleService saleService;
    private final EventDispatcher eventDispatcher;

    public PaymentService(final PaymentRepository paymentRepository,
                          final SaleService saleService,
                          final EventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.saleService = saleService;
        this.eventDispatcher = eventDispatcher;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findPrunedById(final Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
    }

    public List<Payment> findByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public Payment save(final SavePaymentRequest request) {
        Payment savedPayment = paymentRepository.save(request.payment());
        request.productIds().forEach(productId -> saleService.save(new Sale(savedPayment.getId(), productId)));
        eventDispatcher.sendPaymentSavedEvent(new PaymentSavedEvent(savedPayment.getId(), savedPayment.getCustomerId()));
        return savedPayment;
    }
}
