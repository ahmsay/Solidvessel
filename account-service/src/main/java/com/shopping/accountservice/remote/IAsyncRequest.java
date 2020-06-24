package com.shopping.accountservice.remote;

import java.util.Map;

public interface IAsyncRequest {

    String getBaseUrl();

    String getPath();

    Map<String, Object[]> getQueryParameters();

    Class<?> getResponseType();

    IAsyncRequest toPath(String path);

    IAsyncRequest withQueryParameter(String parameterName, Object... parameterValues);

    IAsyncRequest withResponseType(Class<?> responseType);

    <T> T send();
}
