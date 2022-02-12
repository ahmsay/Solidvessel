package com.microshop.accountservice.dto;

import java.util.List;

public record CustomerDTO(Long id, String name, List<PaymentDTO> paymentList, List<OrderDTO> orderList) {
}
