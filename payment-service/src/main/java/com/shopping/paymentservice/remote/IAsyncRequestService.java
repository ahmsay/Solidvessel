package com.shopping.paymentservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String baseUrl);

    <T> T sendRequest(IAsyncRequest request);
}
