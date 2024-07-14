package com.rungroup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.client.id}")
    private String CLIENT_ID;

    @Value("${api.client.secret}")
    private String CLIENT_SECRET;

    @Value("${api.auth.url}")
    private String AUTH_URL;

    public String getAccessToken() throws Exception {
        try {
            return fetchAccessToken();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get access token", e);
        }
    }

    private String fetchAccessToken() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        String body = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(AUTH_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode responseNode = mapper.readTree(response.getBody());

                String accessToken = responseNode.path("access_token").asText();

                return accessToken;
            } else {
                throw new RuntimeException("Failed to fetch access token: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception while fetching access token", e);
        }
    }
}
