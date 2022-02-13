package com.microshop.paymentservice.service;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.event.EventDispatcher;
import com.microshop.paymentservice.event.PaymentSavedEvent;
import com.microshop.paymentservice.repository.PaymentRepository;
import com.microshop.paymentservice.request.AddPaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EventDispatcher eventDispatcher;

    public PaymentService(final PaymentRepository paymentRepository, final EventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.eventDispatcher = eventDispatcher;
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(final Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
    }

    public List<Payment> getByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public Payment add(final AddPaymentRequest request) {
        Payment savedPayment = paymentRepository.save(request.payment());
        eventDispatcher.sendPaymentSavedEvent(new PaymentSavedEvent(savedPayment.getId(), savedPayment.getCustomerId(), request.productIds()));
        return savedPayment;
    }
}
