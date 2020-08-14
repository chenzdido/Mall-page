package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.BuyEvent;
import com.thoughtworks.rslist.dto.BuyEventDto;
import com.thoughtworks.rslist.repository.BuyEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BuyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    BuyEventRepository buyEventRepository;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        buyEventRepository.deleteAll();
    }

    @Test
    public void should_get_BuyList() throws Exception {
        BuyEventDto buyEventDto=BuyEventDto.builder().name("可乐1").price(10).unit("瓶").imgUrl("/see").build();
        buyEventRepository.save(buyEventDto);
        mockMvc.perform(get("/buy/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐1")))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].imgUrl", is("/see")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_delete_BuyEvent() throws Exception {
        BuyEventDto buyEventDto=BuyEventDto.builder().name("可乐1").price(10).unit("瓶").imgUrl("/see").build();
        buyEventRepository.save(buyEventDto);
        buyEventDto=BuyEventDto.builder().name("可乐2").price(12).unit("瓶").imgUrl("/see").build();
        buyEventRepository.save(buyEventDto);
        mockMvc.perform(delete("/buy/delete/'可乐1'"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/buy/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐2")))
                .andExpect(jsonPath("$[0].price", is(12.0)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].imgUrl", is("/see")))
                .andExpect(status().isOk());

    }
}