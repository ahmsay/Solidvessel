package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.adapter.in.payment.rest.mapper.PaymentWebMapper;
import com.solidvessel.payment.adapter.in.payment.rest.request.AcceptPaymentRequest;
import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentDetailResponse;
import com.solidvessel.payment.adapter.in.payment.rest.response.PaymentResponse;
import com.solidvessel.payment.adapter.out.customer.rest.mapper.CustomerWebMapper;
import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommandService;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentQueryPort paymentQueryPort;
    private final KeycloakAdapter keycloakAdapter;
    private final AcceptPaymentCommandService acceptPaymentCommandService;
    private final PaymentWebMapper paymentWebMapper;
    private final CustomerWebMapper customerWebMapper;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/")
    public List<PaymentResponse> getPayments(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return paymentQueryPort.getPayments(QueryOptions.of(pageNumber, pageSize)).stream().map(paymentWebMapper::toResponse).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ofCurrentCustomer")
    public List<PaymentResponse> getPaymentsOfCurrentCustomer() {
        return paymentQueryPort.getByCustomerId(SessionUtil.getCurrentUserId()).stream().map(paymentWebMapper::toResponse).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        return paymentWebMapper.toResponse((paymentQueryPort.getById(id)));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public PaymentDetailResponse getDetailById(@PathVariable Long id) {
        Payment payment = paymentQueryPort.getById(id);
        CustomerResponse customer = customerWebMapper.toResponse(keycloakAdapter.getUser(payment.getCustomerId()));
        return paymentWebMapper.toDetailResponse(payment, customer);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentResponse> getByCustomerId(@PathVariable String customerId) {
        return paymentQueryPort.getByCustomerId(customerId).stream().map(paymentWebMapper::toResponse).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/accept")
    public OperationResult acceptPayment(AcceptPaymentRequest request) {
        return acceptPaymentCommandService.execute(request.toCommand());
    }
}
