package info.etonline.challenges.reversegeocoding.services;

import info.etonline.challenges.reversegeocoding.models.AddressResponse;
import info.etonline.challenges.reversegeocoding.models.AddressDetailsResponse;

import java.util.List;

/**
 * Created by erich on 10/29/2016.
 */
public interface GeocodingDelegateService {
    AddressResponse getReverseGeocodingViaCoordinates(String service, String latitude, String longitude);
    List<AddressDetailsResponse> getReverseHistory();
}
