package com.microshop.paymentservice.dto;

import java.util.List;

public record PaymentDTO(Long id, Double totalCharge, CustomerDTO customer, List<ProductDTO> productList) {
}
