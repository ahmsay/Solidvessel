package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findByPaymentId(Long paymentId);
}
