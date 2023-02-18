package com.solidvessel.payment.infra.adapter.payment.rest;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.customer.port.CustomerPort;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.payment.service.PaymentCommandService;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductPort;
import com.solidvessel.payment.infra.adapter.payment.rest.request.AddPaymentRequest;
import com.solidvessel.shared.infra.rest.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentPort paymentPort;
    private final PaymentCommandService paymentCommandService;
    private final CustomerPort customerPort;
    private final ProductPort productPort;

    public PaymentController(PaymentPort paymentPort, PaymentCommandService paymentCommandService, CustomerPort customerPort, ProductPort productPort) {
        this.paymentPort = paymentPort;
        this.paymentCommandService = paymentCommandService;
        this.customerPort = customerPort;
        this.productPort = productPort;
    }

    @GetMapping()
    public Response<List<PaymentDataModel>> getAll() {
        return new Response<>(paymentPort.getAll());
    }

    @GetMapping("/{id}")
    public Response<PaymentDataModel> getById(@PathVariable final Long id) {
        return new Response<>(paymentPort.getById(id));
    }

    @GetMapping("/{id}/detail")
    public Response<PaymentDetailDataModel> getDetailById(@PathVariable final Long id) {
        PaymentDataModel payment = paymentPort.getById(id);
        CustomerDataModel customer = customerPort.getCustomerOfPayment(payment.customerId());
        List<ProductDataModel> products = productPort.getProductsOfPayment(payment.id());
        return new Response<>(PaymentDetailDataModel.from(payment, customer, products));
    }

    @GetMapping("/ofCustomer/{customerId}")
    public Response<List<PaymentDataModel>> getByCustomerId(@PathVariable final Long customerId) {
        return new Response<>(paymentPort.getByCustomerId(customerId));
    }

    @PostMapping()
    public void add(@RequestBody final AddPaymentRequest request) {
        paymentCommandService.add(request.toCommand());
    }
}
