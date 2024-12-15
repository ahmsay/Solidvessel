package com.solidvessel.account.adapter.in.address.rest;

import com.solidvessel.account.adapter.in.address.rest.request.AddAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.request.UpdateAddressRequest;
import com.solidvessel.account.adapter.in.address.rest.response.AddressResponse;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.account.address.service.AddAddressCommandService;
import com.solidvessel.account.address.service.DeleteAddressCommandService;
import com.solidvessel.account.address.service.SetPrimaryAddressCommandService;
import com.solidvessel.account.address.service.UpdateAddressCommandService;
import com.solidvessel.account.address.service.command.DeleteAddressCommand;
import com.solidvessel.account.address.service.command.SetPrimaryAddressCommand;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
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
    private DeleteAddressCommandService deleteAddressCommandService;

    @MockBean
    private UpdateAddressCommandService updateAddressCommandService;

    @MockBean
    private SetPrimaryAddressCommandService setPrimaryAddressCommandService;

    @Test
    @WithMockCustomer
    void getAddresses() throws Exception {
        var queryOptions = new QueryOptions(0);
        var addresses = List.of(new Address("123", "home", "turkey", "eskisehir", "26200", false));
        when(addressQueryPort.getAddresses(SessionUtil.getCurrentUserId(), queryOptions)).thenReturn(addresses);
        MvcResult mvcResult = mockMvc.perform(
                get("/address")
                        .param("pageNumber", "0")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(addresses.stream().map(AddressResponse::from).toList()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void addAddress() throws Exception {
        var request = new AddAddressRequest("home", "norway", "oslo", "344");
        var savedAddress = Address.builder().id(1L).customerId("123").name("home").country("norway").city("oslo").zipCode("344").build();
        when(addAddressCommandService.execute(request.toCommand())).thenReturn(savedAddress);
        MvcResult mvcResult = mockMvc.perform(
                post("/address")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(AddressResponse.from(savedAddress)), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void deleteAddress() throws Exception {
        when(deleteAddressCommandService.execute(new DeleteAddressCommand(1L, SessionUtil.getCurrentUserId()))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                delete("/address/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void updateAddress() throws Exception {
        var request = new UpdateAddressRequest(1L, "home", "finland", "helsinki", "534");
        var savedAddress = Address.builder().id(1L).customerId("123").name("home").country("finland").city("helsinki").zipCode("534").build();
        when(updateAddressCommandService.execute(request.toCommand())).thenReturn(savedAddress);
        MvcResult mvcResult = mockMvc.perform(
                put("/address")
                        .content(bodyOf(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(AddressResponse.from(savedAddress)), bodyOf(mvcResult));
    }

    @Test
    @WithMockCustomer
    void setPrimaryAddress() throws Exception {
        when(setPrimaryAddressCommandService.execute(new SetPrimaryAddressCommand(1L, SessionUtil.getCurrentUserId()))).thenReturn(OperationResult.defaultSuccessResult());
        MvcResult mvcResult = mockMvc.perform(
                put("/address/1/setPrimary")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(bodyOf(OperationResult.defaultSuccessResult()), bodyOf(mvcResult));
    }
}
