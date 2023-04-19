package com.example.demoprofile;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTests {

    @MockBean
    ProfileService profileService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities = {"SCOPE_profile:read"})
    public void shouldReturnAProfile() throws Exception {
        Mockito.when(profileService.findById("fooId")).thenReturn(new Profile("fooId"));

        mvc.perform(get("/profiles/fooId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("sessionRef","fooSessionRef")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("fooId"));
    }


}
