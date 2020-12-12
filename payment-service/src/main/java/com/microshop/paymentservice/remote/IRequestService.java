package com.microshop.paymentservice.remote;

public interface IRequestService {

    IRequest createRequest(String applicationUrl);

    <T> T sendRequest(IRequest request);
}
