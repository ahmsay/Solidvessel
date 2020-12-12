package com.microshop.inventoryservice.remote;

public interface IRequestService {

    IRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRequest request);
}
