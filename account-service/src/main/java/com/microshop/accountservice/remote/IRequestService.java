package com.microshop.accountservice.remote;

public interface IRequestService {

    IRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRequest request);
}
