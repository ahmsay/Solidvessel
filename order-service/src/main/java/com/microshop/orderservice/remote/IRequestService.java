package com.microshop.orderservice.remote;

public interface IRequestService {

    IRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRequest request);
}
