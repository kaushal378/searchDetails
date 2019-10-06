package com.HarrisburgU.searchDetails.controller;

import com.HarrisburgU.searchDetails.pojos.SearchDetailsRequest;
import com.HarrisburgU.searchDetails.service.SearchDetailsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchDetailsController {
    @Autowired
    SearchDetailsService searchService;

    @Autowired
    SearchDetailsRequest jsonInput;

    @RequestMapping("/searchDetails")
    @ResponseBody
    public String searchDetails(SearchDetailsRequest request){
        String out = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode output = objectMapper.readTree(this.getClass().getResourceAsStream("/searchDetailsOutput.json"));
            return output.toString();
            //out  = searchService.searchPlaces(request);
        }catch(Exception e){
            e.printStackTrace();
        }
        return out;
    }
}
