package info.etonline.challenges.reversegeocoding.services;

import info.etonline.challenges.reversegeocoding.models.AddressDetailsResponse;

/**
 * Created by erich on 10/29/2016.
 */
public interface GeocodingService {
    AddressDetailsResponse getReverseGeocodingViaCoordinates(String latitude, String longitude);
}
