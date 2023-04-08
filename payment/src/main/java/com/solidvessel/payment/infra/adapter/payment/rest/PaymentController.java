package com.solidvessel.payment.infra.adapter.payment.rest;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.customer.port.CustomerPort;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentPort paymentPort;
    private final CustomerPort customerPort;
    private final ProductsPort productsPort;

    @GetMapping()
    public List<PaymentDataModel> getAll() {
        return paymentPort.getAll();
    }

    @GetMapping("/{id}")
    public PaymentDataModel getById(@PathVariable final Long id) {
        return paymentPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public PaymentDetailDataModel getDetailById(@PathVariable final Long id) {
        PaymentDataModel payment = paymentPort.getById(id);
        CustomerDataModel customer = customerPort.getCustomerOfPayment(payment.customerId());
        List<ProductDataModel> products = productsPort.getProductsOfPayment(payment.id());
        return PaymentDetailDataModel.from(payment, customer, products);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentDataModel> getByCustomerId(@PathVariable final Long customerId) {
        return paymentPort.getByCustomerId(customerId);
    }
}
