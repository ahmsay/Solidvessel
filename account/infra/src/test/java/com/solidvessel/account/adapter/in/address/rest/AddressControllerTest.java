package com.solidvessel.account.adapter.in.address.rest;

import com.solidvessel.account.adapter.in.address.rest.request.AddAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.RemoveAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.UpdateAddressRequest;
import com.solidvessel.account.address.datamodel.AddressDataModel;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.AddAddressCommandService;
import com.solidvessel.account.address.service.RemoveAddressCommandService;
import com.solidvessel.account.address.service.UpdateAddressCommandService;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.test.controller.BaseControllerTest;
import com.solidvessel.shared.test.controller.WithMockCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AddressController.class})
public class AddressControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressQueryPort addressQueryPort;

    @MockBean
    private AddAddressCommandService addAddressCommandService;

    @MockBean
    private RemoveAddressCommandService removeAddressCommandService;

    @MockBean
    private UpdateAddressCommandService updateAddressCommandService;

    @Test
    @WithMockCustomer
    public void getAddresses() throws Exception {
        var addresses = List.of(new AddressDataModel("home", "turkey", "eskisehir", "26200"));
        when(addressQueryPort.getAddresses(anyString())).thenReturn(addresses);
        MvcResult mvcResult = mockMvc.perform(
                get("/address")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(addresses), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void addAddress() throws Exception {
        var request = new AddAddressRequest("home", "norway", "oslo", "344");
        when(addAddressCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                post("/address")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void removeAddress() throws Exception {
        var request = new RemoveAddressRequest(1L);
        when(removeAddressCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/address")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    public void updateAddress() throws Exception {
        var request = new UpdateAddressRequest(1L, "home", "finland", "helsinki", "534");
        when(updateAddressCommandService.execute(request.toCommand())).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                put("/address")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
