package com.solidvessel.payment.adapter.in.address.event;

import com.solidvessel.payment.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.payment.address.service.SaveAddressCommandService;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class PrimaryAddressSavedEventConsumerTest extends BaseUnitTest {

    @Mock
    private SaveAddressCommandService saveAddressCommandService;

    @Test
    void consumePrimaryAddressSavedEvent() {
        var event = new PrimaryAddressSavedEvent("123", "69420 Brisbane, Canada");
        var eventConsumer = new PrimaryAddressSavedEventConsumer(saveAddressCommandService);
        eventConsumer.consume(event);
        verify(saveAddressCommandService).execute(event);
    }
}
