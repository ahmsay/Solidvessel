package com.shopping.accountservice.remote;

import java.util.HashMap;
import java.util.Map;

public class AsyncRequest implements IAsyncRequest {

    private IAsyncRequestService requestService;

    private String url;
    private Map<String, Object[]> queryParameters;

    public AsyncRequest(final IAsyncRequestService requestService) {
        this.requestService = requestService;
        url = "";
        queryParameters = new HashMap<>();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Map<String, Object[]> getQueryParameters() {
        return queryParameters;
    }

    @Override
    public AsyncRequest toUrl(final String url) {
        this.url = url;
        return this;
    }

    @Override
    public IAsyncRequest withQueryParam(final String parameterName, final Object... parameterValues) {
        this.queryParameters.put(parameterName, parameterValues);
        return this;
    }

    @Override
    public <T> T send() {
        return requestService.sendRequest(this);
    }
}
