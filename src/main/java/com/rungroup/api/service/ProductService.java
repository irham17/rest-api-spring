package com.rungroup.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthApiService authApiService;

    @Value("${api.product.url}")
    private String API_URL;

    public String getProducts(int page, int limit, String kfa_code) throws Exception {
        try {
            String accessToken = authApiService.getAccessToken();

            System.out.println(accessToken);

            HttpHeaders headers = new HttpHeaders();

            headers.set("Authorization", "Bearer " + accessToken);

            String urlWithParams = String.format("%s?page=%d&limit=%d&kfa_code=%s", API_URL, page, limit, kfa_code);
            System.out.println(urlWithParams);

            ResponseEntity<String> response = restTemplate.exchange(urlWithParams, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "Error fetching products: " + e.getResponseBodyAsString();
        }
    }
}
