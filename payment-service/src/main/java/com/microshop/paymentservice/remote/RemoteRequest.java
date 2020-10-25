package com.microshop.paymentservice.remote;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class RemoteRequest implements IRemoteRequest {

    private final IRemoteRequestService requestService;

    private final String applicationUrl;
    private HttpMethod httpMethod;
    private String path;
    private final Map<String, Object[]> queryParameters;
    private Object body;
    private Class<?> responseType;

    public RemoteRequest(final IRemoteRequestService requestService, final String applicationUrl) {
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
    public RemoteRequest toPath(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public IRemoteRequest withQueryParameter(final String parameterName, final Object... parameterValues) {
        this.queryParameters.put(parameterName, parameterValues);
        return this;
    }

    @Override
    public IRemoteRequest withRequestBody(final Object requestBody) {
        this.body = requestBody;
        return this;
    }

    @Override
    public IRemoteRequest withHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    @Override
    public IRemoteRequest withResponseType(final Class<?> responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public <T> T send() {
        return requestService.sendRequest(this);
    }
}
