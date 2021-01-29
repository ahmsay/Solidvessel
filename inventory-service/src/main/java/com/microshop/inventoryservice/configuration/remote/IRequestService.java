package com.microshop.inventoryservice.configuration.remote;

public interface IRequestService {

    IRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRequest request);
}