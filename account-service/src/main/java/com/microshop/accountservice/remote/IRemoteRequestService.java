package com.microshop.accountservice.remote;

public interface IRemoteRequestService {

    IRemoteRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRemoteRequest request);
}
