package com.shopping.orderservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest();

    <T> T sendRequest(IAsyncRequest request);
}
