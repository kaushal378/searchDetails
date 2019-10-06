package com.HarrisburgU.searchDetails.service;

import com.HarrisburgU.searchDetails.httpBuilder.SearchDetailsBuilder;
import com.HarrisburgU.searchDetails.pojos.StringUtils;
import com.HarrisburgU.searchDetails.pojos.SearchDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@ComponentScan
public class SearchDetailsService {
    private static final String LOG_TAG = "ExampleApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_SEARCH = "/search";

    private static final String OUT_JSON = "/json";

    @Value("${ga.key}")
    private String API_KEY;
    @Value("${ga.url}")
    private String url;

    @Autowired
    SearchDetailsBuilder queryBuilder;

    private final String USER_AGENT = "Mozilla/5.0";

    public String searchPlaces(SearchDetailsRequest request) throws Exception {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode input = objectMapper.readTree(this.getClass().getResourceAsStream("/searchDetailsInput.json"));
        SearchDetailsRequest pojo = objectMapper.convertValue(input, SearchDetailsRequest.class);

        pojo = populateInputFromRequest(request, pojo);

        String inputJson = queryBuilder.buildSearchUrl(pojo);

        URL url = new URL(inputJson);
        conn = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());

        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }

        return jsonResults.toString();
    }

    private SearchDetailsRequest populateInputFromRequest(SearchDetailsRequest request, SearchDetailsRequest pojo) {
        if (StringUtils.isNotEmpty(request.getFields())) {
            pojo.setFields(request.getFields());
        }

        if (StringUtils.isNotEmpty(request.getPlace_id())) {
            pojo.setPlace_id(request.getPlace_id());
        }

        return pojo;
    }
}
