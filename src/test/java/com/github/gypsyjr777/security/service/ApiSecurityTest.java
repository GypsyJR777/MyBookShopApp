package com.github.gypsyjr777.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gypsyjr777.security.model.ContactConfirmationPayload;
import com.github.gypsyjr777.security.model.ContactConfirmationResponse;
import com.github.gypsyjr777.security.model.RegistrationForm;
import com.github.gypsyjr777.security.repository.BookstoreUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class ApiSecurityTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookstoreUserRepository repository;
    @MockBean
    private RegistrationService service;

    private static final RegistrationForm form = new RegistrationForm();

    @BeforeAll
    public static void init() {
        form.setEmail("123456@mail.ru");
        form.setName("123456");
        form.setPhone("+79999999999");
        form.setPass("123456");
    }

    @Test
    public void registerTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        post("/reg")
                                .content(objectMapper.writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Mockito.verify(service, Mockito.times(1)).registerNewUser(any());
    }

    @Test
    public void loginTest() throws Exception {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("");
        doReturn(response)
                .when(service)
                .jwtLogin(any());
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(objectMapper.writeValueAsString(new ContactConfirmationPayload()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Mockito.verify(service, Mockito.times(1)).jwtLogin(any());
        assertEquals(response.getResult(), Objects.requireNonNull(mvcResult.getResponse().getCookie("token")).getValue());
    }

    @Test
    void logoutTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/logout").cookie(new Cookie("token", UUID.randomUUID().toString())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"))
                .andReturn();
        Cookie cookie = mvcResult.getResponse().getCookie("token");

        if (Objects.nonNull(cookie)) {
            assertNull(cookie.getValue());
        }
    }
}
