package com.solidvessel.payment.infra.adapter.payment.db;

import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.CartPort;
import com.solidvessel.payment.infra.adapter.payment.db.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartDBAdapter implements CartPort {

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> getByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerIdAndAccepted(customerId, false).toDomainModel();
    }

    @Override
    public void save(Payment cart) {

    }
}
