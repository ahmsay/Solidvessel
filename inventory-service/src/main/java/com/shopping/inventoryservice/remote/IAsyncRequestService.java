package com.shopping.inventoryservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String applicationUrl);

    <T> T sendRequest(IAsyncRequest request);
}
