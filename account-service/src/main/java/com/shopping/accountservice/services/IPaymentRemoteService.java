package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Payment;

public interface IPaymentRemoteService {

    Payment[] getPaymentsOfCustomer(int id);
}
