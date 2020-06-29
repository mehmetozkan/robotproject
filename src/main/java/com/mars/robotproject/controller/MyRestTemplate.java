package com.mars.robotproject.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mars.robotproject.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyRestTemplate {

    static RestTemplate restTemplate = new RestTemplate();



    public static <T> T getTokenRequest(final String url, final Class<T> returnTypeClass,
                                        final MediaType mediaTypes,
                                        final Map<String, String> headerParams,
                                        final Map<String, Object> autParams, final Map<String, Object> queryParams) throws Exception {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaTypes);
        setHeaderParamsIfExists(headers, headerParams);

        JSONObject personJsonObject = new JSONObject();
        for (Map.Entry<String, Object> pair : autParams.entrySet()) {
            personJsonObject.put(pair.getKey(), pair.getValue());
        }
        final HttpEntity<String> requestEntity = new HttpEntity<>(personJsonObject.toString(), headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        setQueryParamsIfExists(uriBuilder, queryParams);

        final ResponseEntity<T> entity = restTemplate
                .exchange(getUrl(uriBuilder),
                        HttpMethod.POST,
                        requestEntity,
                        returnTypeClass);

        return entity.getBody();
    }

    public static String getRequestWithRawBody(final String url, final String jsonStr,
                                                     final MediaType mediaTypes,
                                                     final Map<String, String> headerParams,
                                                     final Map<String, Object> queryParams) throws Exception {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaTypes);
        setHeaderParamsIfExists(httpHeaders, headerParams);

        final HttpEntity<String> requestEntity = new HttpEntity<>(jsonStr, httpHeaders);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        setQueryParamsIfExists(uriBuilder, queryParams);

        final ResponseEntity<String> entity = restTemplate
                .exchange(getUrl(uriBuilder),
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<String>() {
                        });
        return entity.getBody();
    }

    public static <T> List<T> getRequestWithAut(final String url, final Class<T> returnTypeClass,
                                                final MediaType mediaTypes,
                                                final Map<String, String> headerParams,
                                                final Map<String, Object> queryParams) throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaTypes);
        setHeaderParamsIfExists(headers, headerParams);
        final HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        setQueryParamsIfExists(uriBuilder, queryParams);

        final ResponseEntity<List<T>> entity = restTemplate
                .exchange(getUrl(uriBuilder),
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<T>>() {
                        });
        return entity.getBody();
    }

    private static void setHeaderParamsIfExists(HttpHeaders headers, Map<String, String> headerParams) {
        if (headerParams != null && !headerParams.isEmpty())
            headerParams.entrySet()
                    .forEach(entry -> headers.set(entry.getKey(), entry.getValue()));
    }

    private static void setQueryParamsIfExists(UriComponentsBuilder uriBuilder, Map<String, Object> queryParams) {
        if (queryParams != null && !queryParams.isEmpty())
            queryParams.entrySet()
                    .forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
    }

    private static URI getUrl(UriComponentsBuilder uriBuilder) {
        return uriBuilder.build().encode().toUri();
    }

    /*  private HttpHeaders getHttpHeaders() {
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType( MediaType.APPLICATION_JSON );
          headers.set( "X-RequestId", RequestUtility.createRequestId() );
          headers.set( "X-SourceSystem", mccmSource );
          headers.set( "X-User", mccmSource );
          headers.set( "X-ApplicationId", mccmSource );
          return headers;
      }*/
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        final String baseUrl = "http://localhost:8080";
        final String baseUrl = "http://172.31.70.178:1101";
        final String url = "/ServiceCatalog/Rest/SiebelServices/OrderManagement/GetAdvancePaymentOrderInfo/v1";

        final String fullUrl = baseUrl + url;

        final MediaType mediaType = MediaType.APPLICATION_JSON;

        final Map<String, String> headerParams = new HashMap<String, String>() {{
            put("credentials.applicationId", "siebel");
            put("credentials.user", "Mehmet");
            put("SourceSystem", "siebel");

        }};
        RequestData requestData = new RequestData();
        List<ID> ids = new ArrayList<>();
        ids.add(new ID("QUERY_VALUE","5464008065")); //5469004401
        ids.add(new ID("QUERY_TYPE","2"));
        ids.add(new ID("SHOP_CODE","N010774"));//S900005
        ids.add(new ID("SPARE1","XXX"));
        ids.add(new ID("SPARE2","XXX"));
        requestData.setId(ids);         // Convert object to JSON string
        String jsonStr = mapper.writeValueAsString(requestData);

        //String requestWithRawBody = getRequestWithRawBody(fullUrl, jsonStr, mediaType, headerParams, null);

        ResponseData responseData = fileToObjectConverter(fullUrl,jsonStr,ResponseData.class,mediaType, headerParams,null,true);

        //ResponseData responseData = fileToObjectConverter(requestWithRawBody,ResponseData.class,true);
        //ResponseData responseData = mapper.readValue(requestWithRawBody,ResponseData.class);

        List<ID> ids1 =responseData.getGetAdvancePaymentOrderInfoResponse_v1().getBody().getResponse().getID();
        List<RelatedSalesOrder> relatedSalesOrders= responseData.getGetAdvancePaymentOrderInfoResponse_v1().getBody().getResponse().getRelatedSalesOrder();
        for (RelatedSalesOrder relatedSalesOrder :relatedSalesOrders)
        {
            //SalesOrder SalesOrder =
           for (ID id:relatedSalesOrder.getID()){
                System.out.println(id.toString());
                //System.out.println(id.getSchemeName()+" "+id.getValue());
            }
            System.out.println("-----------------------------------------------");
        }

        System.out.println(responseData);



    }

    public static <T> T ToObjectConverter(final String url, Class<? extends T> clazz,
                                              final MediaType mediaTypes,
                                              final Map<String, String> headerParams,
                                              final Map<String, Object> queryParams,boolean isFromJSON) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            if(isFromJSON){
                mapper = new ObjectMapper();
            } else {
              //  mapper = new XmlMapper();
            }
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(mediaTypes);
            setHeaderParamsIfExists(httpHeaders, headerParams);
            final HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            setQueryParamsIfExists(uriBuilder, queryParams);

            final ResponseEntity<String> entity = restTemplate
                    .exchange(getUrl(uriBuilder),
                            HttpMethod.POST,
                            requestEntity,
                            new ParameterizedTypeReference<String>() {
                            });

            T objectTobeConverted = clazz.getConstructor().newInstance();
            objectTobeConverted = mapper.readValue(entity.getBody(), clazz);

            return objectTobeConverted;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    public static <T> T fileToObjectConverter(final String url, final String jsonStr, Class<? extends T> clazz,
                                              final MediaType mediaTypes,
                                              final Map<String, String> headerParams,
                                              final Map<String, Object> queryParams,boolean isFromJSON) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if(isFromJSON){
                mapper = new ObjectMapper();
            } else {
                //  mapper = new XmlMapper();
            }
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(mediaTypes);
            setHeaderParamsIfExists(httpHeaders, headerParams);
            final HttpEntity<String> requestEntity = new HttpEntity<>(jsonStr, httpHeaders);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            setQueryParamsIfExists(uriBuilder, queryParams);

            final ResponseEntity<String> entity = restTemplate
                    .exchange(getUrl(uriBuilder),
                            HttpMethod.POST,
                            requestEntity,
                            new ParameterizedTypeReference<String>() {
                            });

            T objectTobeConverted = clazz.getConstructor().newInstance();
            objectTobeConverted = mapper.readValue(entity.getBody(), clazz);

            return objectTobeConverted;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}