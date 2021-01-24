package com.microshop.accountservice.configuration.remote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties
public class URL {

    private String accountServiceUrl;
    private String inventoryServiceUrl;
    private String orderServiceUrl;
    private String paymentServiceUrl;

    public String getAccountServiceUrl() {
        return accountServiceUrl;
    }

    public void setAccountServiceUrl(final String accountServiceUrl) {
        this.accountServiceUrl = accountServiceUrl;
    }

    public String getInventoryServiceUrl() {
        return inventoryServiceUrl;
    }

    public void setInventoryServiceUrl(final String inventoryServiceUrl) {
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    public String getOrderServiceUrl() {
        return orderServiceUrl;
    }

    public void setOrderServiceUrl(final String orderServiceUrl) {
        this.orderServiceUrl = orderServiceUrl;
    }

    public String getPaymentServiceUrl() {
        return paymentServiceUrl;
    }

    public void setPaymentServiceUrl(final String paymentServiceUrl) {
        this.paymentServiceUrl = paymentServiceUrl;
    }
}
