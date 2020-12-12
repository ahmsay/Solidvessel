package com.microshop.orderservice.remote;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Request implements IRequest {

    private final IRequestService requestService;

    private final String applicationUrl;
    private HttpMethod httpMethod;
    private String path;
    private final Map<String, Object[]> queryParameters;
    private Object body;
    private Class<?> responseType;

    public Request(final IRequestService requestService, final String applicationUrl) {
        this.requestService = requestService;
        this.applicationUrl = applicationUrl;
        httpMethod = HttpMethod.GET;
        path = "";
        queryParameters = new HashMap<>();
        body = null;
        responseType = String.class;
    }

    @Override
    public String getApplicationUrl() {
        return applicationUrl;
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
    public Request toPath(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public IRequest withQueryParameter(final String parameterName, final Object... parameterValues) {
        this.queryParameters.put(parameterName, parameterValues);
        return this;
    }

    @Override
    public IRequest withRequestBody(final Object requestBody) {
        this.body = requestBody;
        return this;
    }

    @Override
    public IRequest withHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    @Override
    public IRequest withResponseType(final Class<?> responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public <T> T send() {
        return requestService.sendRequest(this);
    }
}
