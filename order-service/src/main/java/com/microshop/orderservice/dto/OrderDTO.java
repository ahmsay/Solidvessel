package com.microshop.orderservice.dto;

public record OrderDTO(Long id, String status, CustomerDTO customer, PaymentDTO payment) {
}
