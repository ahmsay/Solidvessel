package com.microshop.paymentservice.services;

import java.util.List;

public interface IProductService {

    void setPaymentIds(Long paymentId, List<Long> productIds);
}
