package info.etonline.challenges.reversegeocoding.services;

import info.etonline.challenges.reversegeocoding.exceptions.IntegrationException;
import info.etonline.challenges.reversegeocoding.models.AddressDetailsResponse;
import org.json.simple.JSONObject;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by erich on 10/29/2016.
 */
public class GoogleGeocodingServiceImpl implements GeocodingService {
    private String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=AIzaSyCX7LqjAWSQXRKbGonBzxekpo0eazwDq7U";

    public AddressDetailsResponse getReverseGeocodingViaCoordinates(String latitude, String longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(baseURL, latitude, longitude);
        JSONObject googleRet = null;
        try {
            googleRet = restTemplate.getForObject(url, JSONObject.class);
        } catch (Exception e) {
            throw new IntegrationException("Unable to communicate with external Google Geocode service.", e);
        }

        String address = "No results found.";
        if (googleRet == null) {
            return new AddressDetailsResponse(latitude, longitude, address, new Date());
        }

        ArrayList addressList = (ArrayList) googleRet.get("results");
        if (addressList.size()>0) {
            Map addressMap = (HashMap) addressList.get(0);
            address = (String) addressMap.get("formatted_address");
        }
        return new AddressDetailsResponse(latitude, longitude, address, new Date());

    }
}

