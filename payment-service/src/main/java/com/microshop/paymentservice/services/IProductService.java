package com.microshop.paymentservice.services;

import com.microshop.paymentservice.wrapper.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findByPaymentId(Long paymentId);

    void setPaymentIds(Long paymentId, List<Long> productIds);
}
