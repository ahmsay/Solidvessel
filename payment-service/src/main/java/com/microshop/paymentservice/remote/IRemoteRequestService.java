package com.microshop.paymentservice.remote;

public interface IRemoteRequestService {

    IRemoteRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRemoteRequest request);
}
