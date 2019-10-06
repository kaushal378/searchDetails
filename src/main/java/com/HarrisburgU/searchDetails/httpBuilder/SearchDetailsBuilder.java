package com.HarrisburgU.searchDetails.httpBuilder;

import com.HarrisburgU.searchDetails.pojos.SearchDetailsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;

@Service
public class SearchDetailsBuilder {

    @Value("${ga.url}")
    String url;

    public String buildSearchUrl(SearchDetailsRequest params) throws  Exception{
        String out = "";
        try{
            StringBuilder sb =new StringBuilder();
            sb.append(url);
            out = addRequiredParams(sb, params);
        }catch(Exception e){
            throw e;
        }
        return out;
    }

    protected String addRequiredParams(StringBuilder sb, SearchDetailsRequest params) throws Exception{

        sb = addField(sb, "key", params.getKey());
        sb = addField(sb, "fields", params.getFields());
        sb = addField(sb, "place_id", params.getPlace_id());
        return sb.toString();
    }

    protected StringBuilder addField(StringBuilder sb, String name, String value) throws Exception{
        if(!StringUtils.isEmpty(value)){
            int len = sb.length();
            if(sb.toString().length()==0 || (sb.lastIndexOf("&") !=len-1 && sb.lastIndexOf("?")!=len-1)){
                sb.append('&');
            }
            sb.append(name);
            sb.append('=');
            sb.append(URLEncoder.encode(value, "UTF-8"));
        }

        return sb;
    }
}
