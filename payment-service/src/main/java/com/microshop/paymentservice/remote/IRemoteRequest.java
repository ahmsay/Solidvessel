package com.microshop.paymentservice.remote;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface IRemoteRequest {

    String getApplicationUrl();

    HttpMethod getHttpMethod();

    String getPath();

    Map<String, Object[]> getQueryParameters();

    Object getBody();

    Class<?> getResponseType();

    IRemoteRequest withHttpMethod(HttpMethod httpMethod);

    IRemoteRequest toPath(String path);

    IRemoteRequest withQueryParameter(String parameterName, Object... parameterValues);

    IRemoteRequest withRequestBody(Object requestBody);

    IRemoteRequest withResponseType(Class<?> responseType);

    <T> T send();
}
