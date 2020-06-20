package com.shopping.paymentservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest();

    <T> T sendRequest(IAsyncRequest remotingRequest);
}
