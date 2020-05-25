package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Payment;

public interface IPaymentRemoteService {

    Payment getPaymentOfProduct(String productId);
}
