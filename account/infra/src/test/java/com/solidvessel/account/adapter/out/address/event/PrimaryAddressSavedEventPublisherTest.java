package com.solidvessel.account.adapter.out.address.event;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.verify;

public class PrimaryAddressSavedEventPublisherTest extends BaseUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${exchanges.address}")
    private String addressExchangeName;

    @Value("${routing-keys.address.primary-saved}")
    private String primaryAddressSavedRoutingKey;

    @Test
    void publishPrimaryAddressSavedEvent() {
        var event = new PrimaryAddressSavedEvent("123", "6493 Oslo, Norway");
        var eventPublisher = new PrimaryAddressSavedEventPublisher(rabbitTemplate);
        eventPublisher.publish(event);
        verify(rabbitTemplate).convertAndSend(addressExchangeName, primaryAddressSavedRoutingKey, event);
    }
}
