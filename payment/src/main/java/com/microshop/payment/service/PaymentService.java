package com.microshop.payment.service;

import com.microshop.payment.entity.Payment;
import com.microshop.payment.event.EventDispatcher;
import com.microshop.payment.event.PaymentSavedEvent;
import com.microshop.payment.repository.PaymentRepository;
import com.microshop.payment.request.AddPaymentRequest;
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
