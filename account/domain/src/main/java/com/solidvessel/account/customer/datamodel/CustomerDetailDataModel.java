package com.solidvessel.account.customer.datamodel;

import com.solidvessel.account.order.datamodel.OrderDataModel;
import com.solidvessel.account.payment.datamodel.PaymentDataModel;

import java.time.LocalDate;
import java.util.List;

public record CustomerDetailDataModel(Long id, String firstName, String lastName, LocalDate birthDate,
                                      String email, String phoneNumber, List<OrderDataModel> orders,
                                      List<PaymentDataModel> payments) {

    public static CustomerDetailDataModel from(CustomerDataModel customerDataModel, List<OrderDataModel> orders,
                                               List<PaymentDataModel> payments) {
        return new CustomerDetailDataModel(
                customerDataModel.id(),
                customerDataModel.firstName(),
                customerDataModel.lastName(),
                customerDataModel.birthDate(),
                customerDataModel.email(),
                customerDataModel.phoneNumber(),
                orders,
                payments
        );
    }
}
