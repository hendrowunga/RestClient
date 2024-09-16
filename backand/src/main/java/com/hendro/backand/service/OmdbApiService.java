package com.hendro.backand.service;


import com.hendro.backand.config.OmdbApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OmdbApiService {

    @Autowired
    private OmdbApiConfig omdbApiConfig;

    @Autowired
    private RestTemplate restTemplate;

    // Metode untuk mengambil film berdasarkan judul
    public Map<String, Object> getMovieByTitle(String title) {
        String url = String.format("%s?apikey=%s&t=%s", omdbApiConfig.getUrl(), omdbApiConfig.getApiKey(), title);
        return restTemplate.getForObject(url, Map.class);
    }
}
