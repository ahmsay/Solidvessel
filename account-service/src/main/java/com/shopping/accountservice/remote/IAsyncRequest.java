package com.shopping.accountservice.remote;

import java.util.Map;

public interface IAsyncRequest {

    String getUrl();

    Map<String, Object[]> getQueryParameters();

    IAsyncRequest toUrl(String url);

    IAsyncRequest withQueryParam(String paramName, Object... values);

    <T> T send();
}
