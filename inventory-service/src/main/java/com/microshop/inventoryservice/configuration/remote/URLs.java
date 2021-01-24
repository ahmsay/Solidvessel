package com.microshop.inventoryservice.configuration.remote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "services")
public class URLs {

    private String account;
    private String inventory;
    private String order;
    private String payment;

    public String getAccount() {
        return account;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(final String inventory) {
        this.inventory = inventory;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(final String payment) {
        this.payment = payment;
    }
}
