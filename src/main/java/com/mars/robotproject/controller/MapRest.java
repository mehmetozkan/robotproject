package com.mars.robotproject.controller;

import com.mars.robotproject.model.mapquest.Location;
import com.mars.robotproject.model.mapquest.ResultData;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MapRest {
    public static void main(String[] args) {
        //final String baseUrl = "http://open.mapquestapi.com/geocoding/v1/reverse";
        final String baseUrl = "http://open.mapquestapi.com/geocoding/v1/reverse?key=esXsCK3IyhqgmbkSrF6WGzg3MXWG4pwG&location=40.928464,29.309215";
        final MediaType mediaType = MediaType.APPLICATION_JSON;

        final Map<String, Object> queryParams = new HashMap<String, Object>() {{
            put("key", "esXsCK3IyhqgmbkSrF6WGzg3MXWG4pwG");
            put("location", "41.016191,29.167475");

        }};
        String fullUrl = baseUrl ;
        ResultData responseData =MyRestTemplate.ToObjectConverter(fullUrl, ResultData.class,mediaType, null,null,true);
        Location location =responseData.getResults()[0].getLocations()[0];
        System.out.println(location.getStreet()+", "+location.getAdminArea6()+" "+location.getAdminArea5() +" "+ location.getAdminArea4()+" "+location.getAdminArea3()+" "+location.getAdminArea1()+" ,"+location.getPostalCode());

    }
}
