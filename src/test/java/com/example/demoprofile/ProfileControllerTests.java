package com.example.demoprofile;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTests {

    @MockBean
    ProfileService profileService;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnRefCode(){
        Mockito.when(profileService.findById("fooId")).thenReturn(new Profile("fooId"));

        String url = "/profiles/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("sessionRef","fooSessionRef");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Profile> response = restTemplate.withBasicAuth("user","password")
                .exchange(
                url,
                HttpMethod.GET,
                request,
                Profile.class,
                "fooId"
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo("fooId");
    }
}
