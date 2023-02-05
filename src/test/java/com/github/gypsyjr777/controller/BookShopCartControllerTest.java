package com.github.gypsyjr777.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
class BookShopCartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    BookShopCartController bookShopCartController;

    @Test
    void handleAddAndRemoveBookFromCartRequestTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/books/changeBookStatus/cart/book-tni-417"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Cookie cookie = mvcResult.getResponse().getCookie("cartContents");
        assertEquals("book-tni-417", cookie.getValue());

        mvcResult = mockMvc.perform(post("/books/changeBookStatus/cart/remove/book-tni-417"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        cookie = mvcResult.getResponse().getCookie("cartContents");
        assertNull(cookie);
    }
}