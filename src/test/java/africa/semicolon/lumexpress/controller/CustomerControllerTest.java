package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dtos.request.CustomerRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private CustomerRegisterRequest customerRegisterRequest;

    @BeforeEach
    void setUp() {
        customerRegisterRequest = CustomerRegisterRequest.builder()
                .email("augustineezekiel@763gmail.com")
                .password("incorrect")
                .country("USA")
                .build();
    }

    @Test
    void register() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/customer/")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customerRegisterRequest)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andDo(print());
    }
}