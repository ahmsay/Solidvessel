package com.solidvessel.account.domain.customer.datamodel;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;

import java.util.List;

public record CustomerDetailDataModel(Long id, String firstName, String lastName, List<OrderDataModel> orders,
                                      List<PaymentDataModel> payments) {

    public static CustomerDetailDataModel from(CustomerDataModel customerDataModel, List<OrderDataModel> orders,
                                               List<PaymentDataModel> payments) {
        return new CustomerDetailDataModel(
                customerDataModel.id(),
                customerDataModel.firstName(),
                customerDataModel.lastName(),
                orders,
                payments
        );
    }
}
