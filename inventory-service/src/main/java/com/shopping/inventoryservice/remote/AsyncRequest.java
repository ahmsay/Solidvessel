package com.shopping.inventoryservice.remote;

import java.util.HashMap;
import java.util.Map;

public class AsyncRequest implements IAsyncRequest {

    private IAsyncRequestService requestService;

    private String baseUrl;
    private String path;
    private Map<String, Object[]> queryParameters;
    private Class<?> responseType;

    public AsyncRequest(final IAsyncRequestService requestService, final String baseUrl) {
        this.requestService = requestService;
        this.baseUrl = baseUrl;
        path = "";
        queryParameters = new HashMap<>();
        responseType = String.class;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Map<String, Object[]> getQueryParameters() {
        return queryParameters;
    }

    @Override
    public Class<?> getResponseType() { return responseType; }

    @Override
    public AsyncRequest toPath(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public IAsyncRequest withQueryParameter(final String parameterName, final Object... parameterValues) {
        this.queryParameters.put(parameterName, parameterValues);
        return this;
    }

    @Override
    public IAsyncRequest withResponseType(final Class<?> responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public <T> T send() {
        return requestService.sendRequest(this);
    }
}
