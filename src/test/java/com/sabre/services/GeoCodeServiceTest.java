package com.sabre.services;

import org.apache.http.client.methods.HttpPost;
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
        assertTrue(geoCodeService.returnLattitudeAndLongitude("KRK" ).get().getKey() < 50.1
                && geoCodeService.returnLattitudeAndLongitude("KRK" ).get().getKey() > 50.05);
    }

    @Test
    // Latitude and longitude for KRK Balice new Pair<>(50.077778, 19.784722)
    void testLongitude() {
        assertTrue(geoCodeService.returnLattitudeAndLongitude("KRK" ).get().getValue() < 19.8
                && geoCodeService.returnLattitudeAndLongitude("KRK" ).get().getValue() > 19.7);
    }

    @Test
    void parseResponseAndFindLatitude() {
        assertEquals( new Double(50.078056 ) ,geoCodeService.parseResponseAndFindLatitude( getResponse() ) );
    }

    @Test
    void parseResponseAndFindLongitude() {
        assertEquals( new Double(19.786111 ) ,geoCodeService.parseResponseAndFindLongitude( getResponse() ) );
    }

    @Test
    void parseShorterResult() {
        assertEquals( new Double(50.078056 ) , geoCodeService.parseShorterResult( "latitude=50.078056," ) );
    }

    @Test
    void resultOfResponseShouldntBeNull() {
        assertNotNull( geoCodeService.getResponseFromSabreApi("AMM") );
    }

    @Test
    void shouldAddAuthorizationHeader(){
        HttpPost httpPost = new HttpPost();
        geoCodeService.setRequestHeaders(httpPost);
        assertTrue( httpPost.containsHeader("authorization") );
    }

    @Test
    void shouldAddContentTypeHeader(){
        HttpPost httpPost = new HttpPost();
        geoCodeService.setRequestHeaders(httpPost);
        assertTrue( httpPost.containsHeader("Content-Type") );
    }

    @NotNull
    private String getResponse(){
        return "[{GeoCodeRS={status=ONE_PLACE_FOUND, Place=[{confidenceFactor=ADDRESS_QUALITY, latitude=50.078056, longitude=19.786111, Name=Krakow, Category=AIR, Id=KRK, City=Krakow, Country=PL}]}}]";
    }



}