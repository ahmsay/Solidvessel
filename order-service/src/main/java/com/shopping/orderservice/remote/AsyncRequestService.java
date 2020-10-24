package com.shopping.orderservice.remote;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class AsyncRequestService implements IAsyncRequestService {

    private final RestTemplate restTemplate;

    public AsyncRequestService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IAsyncRequest createRequest(final String applicationUrl) {
        return new AsyncRequest(this, applicationUrl);
    }

    @Override
    public <T> T sendRequest(final IAsyncRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getApplicationUrl() + request.getPath());
        for (Map.Entry<String, Object[]> entry : request.getQueryParameters().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        URI uri = builder.build().encode().toUri();
        HttpEntity<Object> entity = null;
        if (request.getBody() != null) {
            entity = new HttpEntity<>(request.getBody());
        }
        return (T) restTemplate.exchange(uri, request.getHttpMethod(), entity, request.getResponseType()).getBody();
    }
}
