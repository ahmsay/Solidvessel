package com.shopping.accountservice.remote;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class AsyncRequestService implements IAsyncRequestService {

    private RestTemplate restTemplate;

    public AsyncRequestService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IAsyncRequest createRequest() {
        return new AsyncRequest(this);
    }

    @Override
    public <T> T sendRequest(final IAsyncRequest remotingRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(remotingRequest.getUrl());
        for (Map.Entry<String, Object[]> entry : remotingRequest.getQueryParameters().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        URI uri = builder.build().encode().toUri();
        ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
