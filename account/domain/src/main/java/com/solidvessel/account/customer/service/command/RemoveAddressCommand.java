package com.solidvessel.account.customer.service.command;

public record RemoveAddressCommand(String name, Long customerId) {
}