package com.hendro.backand.controller;

import com.hendro.backand.service.OmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OmdbApiController {

    @Value("${omdbapi.url}")
    private String omdbApiUrl;

    @Value("${omdbapi.apiKey}")
    private String apiKey;

    @GetMapping("/movie")
    public ResponseEntity<String> getMovie(@RequestParam String title) {
        try {
            String url = String.format("%s?s=%s&apikey=%s", omdbApiUrl, title, apiKey);
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching movie data");
        }
    }
}

//@RestController
//public class OmdbApiController {
//
//    @Autowired
//    private OmdbApiService omdbApiService;
//
//    // Endpoint untuk mencari film berdasarkan judul
//    @GetMapping("/api/movie")
//    public ResponseEntity<Map<String, Object>> getMovie(@RequestParam String title) {
//        Map<String, Object> movieData = omdbApiService.getMovieByTitle(title);
//        return ResponseEntity.ok(movieData);
//    }
//}
