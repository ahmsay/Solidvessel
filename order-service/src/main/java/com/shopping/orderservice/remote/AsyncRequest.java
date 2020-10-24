package com.shopping.orderservice.remote;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class AsyncRequest implements IAsyncRequest {

    private final IAsyncRequestService requestService;

    private final String application;
    private HttpMethod httpMethod;
    private String path;
    private final Map<String, Object[]> queryParameters;
    private Object body;
    private Class<?> responseType;

    public AsyncRequest(final IAsyncRequestService requestService, final String application) {
        this.requestService = requestService;
        this.application = application;
        httpMethod = HttpMethod.GET;
        path = "";
        queryParameters = new HashMap<>();
        body = null;
        responseType = String.class;
    }

    @Override
    public String getApplication() {
        return application;
    }

    @Override
    public HttpMethod getHttpMethod() { return httpMethod; }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Map<String, Object[]> getQueryParameters() {
        return queryParameters;
    }

    @Override
    public Object getBody() {
        return body;
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
    public IAsyncRequest withRequestBody(final Object requestBody) {
        this.body = requestBody;
        return this;
    }

    @Override
    public IAsyncRequest withHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
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
