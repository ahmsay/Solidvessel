package com.shopping.paymentservice.remote;

import org.springframework.http.HttpMethod;
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
    public IAsyncRequest createRequest(final String baseUrl) {
        return new AsyncRequest(this, baseUrl);
    }

    @Override
    public <T> T sendRequest(final IAsyncRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getBaseUrl() + request.getPath());
        for (Map.Entry<String, Object[]> entry : request.getQueryParameters().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        URI uri = builder.build().encode().toUri();
        return (T) restTemplate.exchange(uri, HttpMethod.GET, null, request.getResponseType()).getBody();
    }
}
