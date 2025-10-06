package com.fiap.tech_challenge.interfaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech_challenge.interfaces.dto.UserTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserTypeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void mustCreateUserType() throws Exception {
        UserTypeDto createDto = new UserTypeDto(null, "owner");
        String createJson = objectMapper.writeValueAsString(createDto);

        mockMvc.perform(
                    post("/api/type-user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createJson)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("owner"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void mustListAllUserTypes() throws Exception {
        UserTypeDto owner = new UserTypeDto(null, "owner");
        UserTypeDto customer = new UserTypeDto(null, "customer");

        mockMvc.perform(
                    post("/api/type-user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(owner))
                ).andExpect(status().isOk());

        mockMvc.perform(
                    post("/api/type-user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customer))
                ).andExpect(status().isOk());

        mockMvc.perform(
                    get("/api/type-user"))
                    .andExpect(status().isOk()
                ).andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("owner", "custumer")));

    }

    @Test
    void mustUpdateUserType() throws Exception {
        UserTypeDto createDto = new UserTypeDto(null, "customer");

        String createdJson = mockMvc.perform(
                post("/api/type-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto))
            ).andReturn()
            .getResponse()
            .getContentAsString();

        UserTypeDto createdDto = objectMapper.readValue(createdJson, UserTypeDto.class);

        UserTypeDto updatedDto = new UserTypeDto(createdDto.id(), "owner");
        mockMvc.perform(
                put("/api/type-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto))
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("owner"));
    }

    @Test
    void mustDeleteUserType() throws Exception {
        UserTypeDto createDto = new UserTypeDto(null, "customer");
        String createdJson = mockMvc.perform(
                post("/api/type-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto))
            ).andReturn()
            .getResponse()
            .getContentAsString();

        UserTypeDto createdDto = objectMapper.readValue(createdJson, UserTypeDto.class);

        mockMvc.perform(
                delete("/api/type-user/{id}", createdDto.id())
            ).andExpect(status().isOk());

        mockMvc.perform(
                get("/api/type-user/{id}", createdDto.id())
            ).andExpect(status().isNotFound());
    }
}

