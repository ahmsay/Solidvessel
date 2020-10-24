package com.shopping.inventoryservice.remote;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface IAsyncRequest {

    String getApplicationUrl();

    HttpMethod getHttpMethod();

    String getPath();

    Map<String, Object[]> getQueryParameters();

    Object getBody();

    Class<?> getResponseType();

    IAsyncRequest withHttpMethod(HttpMethod httpMethod);

    IAsyncRequest toPath(String path);

    IAsyncRequest withQueryParameter(String parameterName, Object... parameterValues);

    IAsyncRequest withRequestBody(Object requestBody);

    IAsyncRequest withResponseType(Class<?> responseType);

    <T> T send();
}
