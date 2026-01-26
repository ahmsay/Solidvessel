package com.solidvessel.payment.adapter.in.payment.rest.mapper;

import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentDetailResponse;
import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentResponse;
import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.payment.payment.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentWebMapper {

    PaymentResponse toResponse(Payment payment);

    PaymentDetailResponse toDetailResponse(Payment payment, CustomerResponse customer);
}
