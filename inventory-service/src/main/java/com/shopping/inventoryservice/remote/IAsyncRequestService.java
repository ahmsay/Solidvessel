package com.shopping.inventoryservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest();

    <T> T sendRequest(IAsyncRequest request);
}
