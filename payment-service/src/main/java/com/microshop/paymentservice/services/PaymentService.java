package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;
import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.dto.ProductDTO;
import com.microshop.paymentservice.dto.SavePaymentDTO;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.event.EventDispatcher;
import com.microshop.paymentservice.event.PaymentSavedEvent;
import com.microshop.paymentservice.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final EventDispatcher eventDispatcher;

    public PaymentService(final PaymentRepository paymentRepository, final ProductService productService,
                          final CustomerService customerService, final SaleService saleService,
                          final EventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.eventDispatcher = eventDispatcher;
    }

    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public PaymentDTO findById(final Long id) {
        Payment payment = findPrunedById(id);
        CustomerDTO customer = customerService.findById(payment.getCustomerId());
        List<ProductDTO> productList = findProductsOfPayment(payment.getId());
        return new PaymentDTO(payment.getId(), payment.getTotalCharge(), customer, productList);
    }

    public Payment findPrunedById(final Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
    }

    public Iterable<Payment> findByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public Payment save(final SavePaymentDTO savePaymentDTO) {
        Payment savedPayment = paymentRepository.save(savePaymentDTO.getPayment());
        savePaymentDTO.getProductIds().forEach(productId -> saleService.save(new Sale(savedPayment.getId(), productId)));
        eventDispatcher.sendPaymentSavedEvent(new PaymentSavedEvent(savedPayment.getId(), savedPayment.getCustomerId()));
        return savedPayment;
    }

    private List<ProductDTO> findProductsOfPayment(final Long paymentId) {
        List<Sale> sales = (List<Sale>) saleService.findByPaymentId(paymentId);
        List<Long> productIds = sales.stream().map(Sale::getProductId).collect(Collectors.toList());
        return productService.findByIds(productIds);
    }
}
