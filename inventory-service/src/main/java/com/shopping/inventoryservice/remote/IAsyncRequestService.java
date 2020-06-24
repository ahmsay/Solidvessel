package com.shopping.inventoryservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String baseUrl);

    <T> T sendRequest(IAsyncRequest request);
}
