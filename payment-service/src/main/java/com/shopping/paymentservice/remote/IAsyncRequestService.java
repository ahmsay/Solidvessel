package com.shopping.paymentservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String applicationUrl);

    <T> T sendRequest(IAsyncRequest request);
}
