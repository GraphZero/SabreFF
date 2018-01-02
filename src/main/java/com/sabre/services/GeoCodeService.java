package com.sabre.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final String token = "Bearer T1RLAQIAqDp6VOUxGF903eBQIZeBFYOlWBADx/QPZpOu79+EmUWfoP2CAADA0VDFKXb7NIveEHpyx1zNtFEQ9+CDGIIBoUkZds2EI1/Tsn95+jjx5T8fh9Urc1gC7xJBbvvPMT1VH3WKHuJdNZw4BcP/MI4l05OebulWEY1XZfG0+HXt9dpSYCCPWjRzi7auAylON2JTqDtN9kl4BSpKkRD9gguxQ+n8mfUzi/dbrElgD0sbGdXxHKUfJgqWWHfWZ/hUrYP2q5fxjhAZxu6Y8syB+4HEkT7fU6AZPGJYV95RXgSVkLbs0IqFNgny";
    private final String requestUrl = "https://api.test.sabre.com/v1/lists/utilities/geocode/locations";
    private static Logger logger = LogManager.getLogger(GeoCodeService.class);

    public Pair<Double, Double> returnLattitudeAndLongitude(String airPortId) {
        HashMap<String, Object> result = null;
        try {
            result = new ObjectMapper().readValue(getResponseFromSabreApi(airPortId)
                    .getEntity().getContent(), HashMap.class);
        } catch (IOException e) {
            logger.error("Couldnt get content");
        }
        System.out.println(result.get("Results").toString());
        return new Pair<>(parseResponseAndFindLatitude(result.get("Results").toString()),
                parseResponseAndFindLongitude(result.get("Results").toString()));
    }

    private void setRequestHeaders(HttpPost request){
        request.addHeader("authorization", token);
        request.addHeader("Content-Type", "application/json");
    }

    protected HttpResponse getResponseFromSabreApi(String airPortId){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(requestUrl);
        StringEntity input = null;
        try {
            input = new StringEntity(requestBody(airPortId));
        } catch (UnsupportedEncodingException e) {
            logger.error("Couldnt parse request.");
        }
        setRequestHeaders(request);
        request.setEntity(input);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            logger.error("Couldnt get response from Sabre api.");
        }
        return null;
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
    protected Double parseResponseAndFindLatitude(String response) {
        Pattern p = Pattern.compile("latitude=[0-9]*.[0-9]*,+");
        Matcher m = p.matcher(response);
        if (m.find()) {
            return parseShorterResult(m.group());
        }
        return null;
    }

    @Nullable
    protected Double parseResponseAndFindLongitude(String response) {
        Pattern p = Pattern.compile("longitude=[0-9]*.[0-9]*,+");
        Matcher m = p.matcher(response);
        if (m.find()) {
            return parseShorterResult(m.group());
        }
        return null;
    }

    @Nullable
    protected Double parseShorterResult(String response) {
        Pattern pa = Pattern.compile("[0-9]*.[0-9]*,+");
        Matcher ma = pa.matcher(response);
        if (ma.find()) {
            return Double.parseDouble(ma.group().replaceAll(",", ""));
        }
        return null;
    }


}
