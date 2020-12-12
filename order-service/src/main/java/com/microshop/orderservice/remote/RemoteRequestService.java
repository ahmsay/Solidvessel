package com.microshop.orderservice.remote;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class RemoteRequestService implements IRemoteRequestService {

    private final RestTemplate restTemplate;

    public RemoteRequestService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IRemoteRequest createRequest(final String applicationUrl) {
        return new RemoteRequest(this, applicationUrl);
    }

    @Override
    public <T> T sendRequest(final IRemoteRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getApplicationUrl() + request.getPath());
        setQueryParameters(request, builder);
        URI uri = builder.build().encode().toUri();
        HttpEntity<Object> entity = prepareRequestEntity(request.getBody());
        return (T) restTemplate.exchange(uri, request.getHttpMethod(), entity, request.getResponseType()).getBody();
    }

    private void setQueryParameters(final IRemoteRequest request, final UriComponentsBuilder builder) {
        for (Map.Entry<String, Object[]> entry : request.getQueryParameters().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
    }

    private HttpEntity<Object> prepareRequestEntity(final Object body) {
        return body != null ? new HttpEntity<>(body) : null;
    }
}
