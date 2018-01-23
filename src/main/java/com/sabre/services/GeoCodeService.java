package com.sabre.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.http.Header;
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
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates requests to Sabre api to get airports latitudes and longitudes.
 */

@Service
public class GeoCodeService {
    private final String token = "Bearer T1RLAQLGin7ZH3kVjtjGMyiTLzKYV0oJeBDoDUKXE+0ChKEh3Gq3atu6AADAWlztfJTAKn0up8PayxiYuHXZgCahQyOqo/NBwX2Q9FQfcJP8a5s3qSlwHgPEEjXfUBDmQ4YeRpFHMAvyRxv03Czg9waoW1qaFRIkyxo7zzaW1Hl3qppptHhUzGgFrghmQ3khvI9/8cpbbHJZ+/kUH8Ync9b1V3PoRmUvq5MjJ4ZaZ524ouw1FZAB4WEmcFVRxHdPUitnL9B0uZvNK5fl8+I9uShdwNGKKf7hi/Fgh4oetmmutadBNS654rraZOit";
    private final String requestUrl = "https://api.test.sabre.com/v1/lists/utilities/geocode/locations";
    private static Logger logger = LogManager.getLogger(GeoCodeService.class);

    public Optional<Pair<Double, Double>> returnLattitudeAndLongitude(String airPortId) {
        HashMap<String, Object> result = null;
        try {
            result = new ObjectMapper()
                    .readValue(getResponseFromSabreApi(airPortId).getEntity().getContent(), HashMap.class);
        } catch (IOException e) {
            logger.error("Couldnt get content");
        }
        return Optional
                .ofNullable(result.get("Results"))
                .map( x -> new Pair<>(parseResponseAndFindLatitude(x.toString()),
                    parseResponseAndFindLongitude(x.toString())));
    }


    protected HttpResponse getResponseFromSabreApi(String airPortId){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(requestUrl);
        StringEntity input = null;
        try {
            input = new StringEntity(requestBody(airPortId.trim()));
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

    protected void setRequestHeaders(HttpPost request){
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
    protected Double parseResponseAndFindLatitude(String response) {
        Pattern p = Pattern.compile("latitude=.[0-9]*.[0-9]*,+");
        Matcher m = p.matcher(response);
        if (m.find()) {
            return parseShorterResult(m.group());
        }
        return null;
    }

    @Nullable
    protected Double parseResponseAndFindLongitude(String response) {
        Pattern p = Pattern.compile("longitude=.[0-9]*.[0-9]*,+");
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
