package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.CustomerDTO;
import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.entity.Order;
import com.microshop.accountservice.entity.Payment;
import com.microshop.accountservice.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final IPaymentService paymentService;
    private final IOrderService orderService;

    public CustomerService(final ICustomerRepository customerRepository, final IPaymentService paymentService, final IOrderService orderService) {
        this.customerRepository = customerRepository;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO findById(final Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        List<Payment> paymentList = paymentService.findByCustomerId(customer.getId());
        List<Order> orderList = orderService.findByCustomerId(customer.getId());
        return new CustomerDTO(customer.getId(), customer.getName(), paymentList, orderList);
    }

    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(customer);
    }
}
