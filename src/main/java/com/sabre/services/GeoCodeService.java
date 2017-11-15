package com.sabre.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrzej on 2017-11-09.
 */

@Service
public class GeoCodeService {
    private final String token = "Bearer T1RLAQKE/yKcuq/qI1iolCdA5nAnYH5nbBBqI+rL1LuPptZ7Dxa5aqj4AADAlVylMTrrj1bK7yTJEbBTBeAhfk1vxt1XEA+2OP5xuYFQ+creg+xXjCbqh7EmIhkA4H/JRqoWcg9BoiQ3zVGEK9dNQeijoDsqBh6wC6X+Kd62AFTpSRecYptdeiISmVWfbOsrhCaUtAs1/tEf7k7JXsSU2xB+ItjVlp7pwc8qW4BI+DpTFR8q8ykG0BznQTIaZITECDk9B2DCY/0/rTaSbvaUGAonoYv1GDpYBkdnQqllX9N2j3MfQfmjfd1Jh0X5";
    private final String requestUrl = "https://api.test.sabre.com/v1/lists/utilities/geocode/locations";

    public Pair<Double, Double> returnLattitudeAndLongitude(String airPortId) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(requestUrl);
        HttpResponse response;
        StringEntity input = new StringEntity(requestBody(airPortId));

        setRequestHeaders(request);
        request.setEntity(input);

        response = httpClient.execute(request);
        HashMap<String, Object> result = new ObjectMapper().readValue(response.getEntity().getContent(), HashMap.class);

        return new Pair<>(parseResponseAndFindLatitude(result.get("Results").toString()) * Math.PI / 180,
                parseResponseAndFindLongitude(result.get("Results").toString()) * Math.PI / 180);
    }

    private void setRequestHeaders(HttpPost request){
        request.addHeader("authorization", token);
        request.addHeader("Content-Type", "application/json");
    }

    @NotNull
    private String requestBody( String airPortId ){
        return "[{" +
                "    \"GeoCodeRQ\": {" +
                "        \"PlaceById\": {" +
                "            \"Id\": \"" + airPortId + "\"," +
                "            \"BrowseCategory\": {" +
                "                \"name\": \"AIR\"" +
                "            }" +
                "        }" +
                "    }" +
                "}]";
    }

    @Nullable
    private Double parseResponseAndFindLatitude(String response) {
        Pattern p = Pattern.compile("latitude=[0-9]*.[0-9]*,+");
        Matcher m = p.matcher(response);
        if (m.find()) {
            return parseShorterResult(m.group());
        }
        return null;
    }

    @Nullable
    private Double parseResponseAndFindLongitude(String response) {
        Pattern p = Pattern.compile("longitude=[0-9]*.[0-9]*,+");
        Matcher m = p.matcher(response);
        if (m.find()) {
            return parseShorterResult(m.group());
        }
        return null;
    }

    @Nullable
    private Double parseShorterResult(String response) {
        Pattern pa = Pattern.compile("[0-9]*.[0-9]*,+");
        Matcher ma = pa.matcher(response);
        if (ma.find()) {
            return Double.parseDouble(ma.group().replaceAll(",", ""));
        }
        return null;
    }


}
