package com.microshop.orderservice.remote;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface IRequest {

    String getApplicationUrl();

    HttpMethod getHttpMethod();

    String getPath();

    Map<String, Object[]> getQueryParameters();

    Object getBody();

    Class<?> getResponseType();

    IRequest withHttpMethod(HttpMethod httpMethod);

    IRequest toPath(String path);

    IRequest withQueryParameter(String parameterName, Object... parameterValues);

    IRequest withRequestBody(Object requestBody);

    IRequest withResponseType(Class<?> responseType);

    <T> T send();
}
