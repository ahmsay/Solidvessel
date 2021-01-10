package com.microshop.accountservice.services;

import com.microshop.accountservice.wrapper.OrderDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> findByCustomerId(Long customerId);
}
