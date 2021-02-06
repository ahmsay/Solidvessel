package com.microshop.paymentservice.repositories;

import com.microshop.paymentservice.entity.PaymentProduct;
import com.microshop.paymentservice.entity.PaymentProductId;
import org.springframework.data.repository.CrudRepository;

public interface IPaymentProductRepository extends CrudRepository<PaymentProduct, PaymentProductId> {

}
