package com.shopping.accountservice.remote;

public interface IAsyncRequestService {

    IAsyncRequest createRequest(String applicationUrl);

    <T> T sendRequest(IAsyncRequest request);
}
