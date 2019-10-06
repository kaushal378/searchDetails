package com.HarrisburgU.searchDetails.pojos;

import org.springframework.stereotype.Component;

@Component
public class SearchDetailsRequest {
    private String key;
    private String fields;
    private String place_id;

    public String getFields() {return fields; }
    public String getKey() { return key; }
    public String getPlace_id() { return place_id; }
    public void setFields(String fields) { this.fields = fields; }
    public void setKey(String key) { this.key = key; }
    public void setPlace_id(String place_id) { this.place_id = place_id; }
}
