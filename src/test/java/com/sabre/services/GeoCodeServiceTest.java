package com.sabre.services;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.*;

class GeoCodeServiceTest {
    static GeoCodeService geoCodeService;


    @BeforeAll
    static void setGeoCodeService(){
        geoCodeService = new GeoCodeService();
    }

    @Test
    // Latitude and longitude for KRK Balice new Pair<>(50.077778, 19.784722)
    void testLattitude() {
        assertTrue(geoCodeService.returnLattitudeAndLongitude("KRK" ).getKey() < 50.1
                && geoCodeService.returnLattitudeAndLongitude("KRK" ).getKey() > 50.05);
    }

    @Test
    // Latitude and longitude for KRK Balice new Pair<>(50.077778, 19.784722)
    void testLongitude() {
        assertTrue(geoCodeService.returnLattitudeAndLongitude("KRK" ).getValue() < 19.8
                && geoCodeService.returnLattitudeAndLongitude("KRK" ).getValue() > 19.7);
    }

    @Test
    void parseResponseAndFindLatitude() {
        System.out.println(getResponse());
        assertEquals( new Double(32.896111 ) ,geoCodeService.parseResponseAndFindLatitude( getResponse() ) );
    }

    @Test
    void parseResponseAndFindLongitude() {
    }

    @Test
    void parseShorterResult() {
    }

    @Test
    void resultOfResponseShouldntBeNull() {
        assertNotNull( geoCodeService.getResponseFromSabreApi("AMM") );
    }

    @NotNull
    private String getResponse(){
        return "{\n" +
                "  \"Results\":[\n" +
                "    {\n" +
                "      \"GeoCodeRS\":{\n" +
                "        \"status\":\"ONE_PLACE_FOUND\",\n" +
                "        \"Place\":[\n" +
                "          {\n" +
                "            \"confidenceFactor\":\"ADDRESS_QUALITY\",\n" +
                "            \"latitude\":32.896111,\n" +
                "            \"longitude\":-97.041111,\n" +
                "            \"Name\":\"Dallas/Fort Worth Intl.\",\n" +
                "            \"Category\":\"AIR\",\n" +
                "            \"Id\":\"DFW\",\n" +
                "            \"City\":\"Dallas\",\n" +
                "            \"State\":\"TX\",\n" +
                "            \"Country\":\"US\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"GeoCodeRS\":{\n" +
                "        \"status\":\"ONE_PLACE_FOUND\",\n" +
                "        \"Place\":[\n" +
                "          {\n" +
                "            \"confidenceFactor\":\"ADDRESS_QUALITY\",\n" +
                "            \"latitude\":33.640278,\n" +
                "            \"longitude\":-84.426944,\n" +
                "            \"Name\":\"Atlanta\",\n" +
                "            \"Category\":\"AIR\",\n" +
                "            \"Id\":\"ATL\",\n" +
                "            \"City\":\"Atlanta\",\n" +
                "            \"State\":\"GA\",\n" +
                "            \"Country\":\"US\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Links\":[\n" +
                "    {\n" +
                "      \"rel\":\"self\",\n" +
                "      \"href\":\"https://api.havail.sabre.com/v1/lists/utilities/geocode/locations\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"rel\":\"linkTemplate\",\n" +
                "      \"href\":\"https://api.havail.sabre.com/v1/lists/utilities/geocode/locations\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

}