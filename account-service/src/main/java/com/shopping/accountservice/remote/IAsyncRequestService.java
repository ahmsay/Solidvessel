package com.shopping.accountservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String baseUrl);

    <T> T sendRequest(IAsyncRequest request);
}
