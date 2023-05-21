package com.solidvessel.account.domain.customer.service.command;

public record RemoveAddressCommand(String name, Long customerId) {
}
