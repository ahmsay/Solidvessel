package com.solidvessel.account.customer.datamodel;

import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.order.datamodel.OrderDataModel;
import com.solidvessel.account.order.model.Order;
import com.solidvessel.account.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.payment.model.Payment;

import java.time.LocalDate;
import java.util.List;

public record CustomerDetailDataModel(String id, String firstName, String lastName, LocalDate birthDate,
                                      String email, String phoneNumber, List<OrderDataModel> orders,
                                      List<PaymentDataModel> payments) {

    public static CustomerDetailDataModel from(Customer customer, List<Order> orders,
                                               List<Payment> payments) {
        return new CustomerDetailDataModel(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                orders.stream().map(OrderDataModel::from).toList(),
                payments.stream().map(PaymentDataModel::from).toList()
        );
    }
}
