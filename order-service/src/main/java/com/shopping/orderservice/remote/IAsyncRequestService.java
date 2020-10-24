package com.shopping.orderservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String applicationUrl);

    <T> T sendRequest(IAsyncRequest request);
}
