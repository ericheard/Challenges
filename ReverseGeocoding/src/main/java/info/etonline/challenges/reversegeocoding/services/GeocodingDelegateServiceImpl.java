package info.etonline.challenges.reversegeocoding.services;

import info.etonline.challenges.reversegeocoding.models.AddressResponse;
import info.etonline.challenges.reversegeocoding.models.AddressDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by erich on 10/29/2016.
 */
@Service
public class GeocodingDelegateServiceImpl implements GeocodingDelegateService {
    private List<AddressDetailsResponse> history = new ArrayList<AddressDetailsResponse>();

    private Map<String, GeocodingService> geocodingMap = new HashMap<String, GeocodingService>();

    public GeocodingDelegateServiceImpl() {
        geocodingMap.put("google", new GoogleGeocodingServiceImpl());
        geocodingMap.put("other", new OtherGeocodingServiceImpl());
    }

    public AddressResponse getReverseGeocodingViaCoordinates(String service, String latitude, String longitude) {

        GeocodingService geocodingService = getGeocodingService(service);
        AddressDetailsResponse info = geocodingService.getReverseGeocodingViaCoordinates(latitude, longitude);
        addToHistory(info);

        return new AddressResponse(info.getAddress());
    }

    private GeocodingService getGeocodingService(String service) {
        // Chosse to be responsive by aways falling back to google
        GeocodingService geocodingService = null;
        if (service != null) {
            geocodingService = geocodingMap.get(service);
        }
        if (geocodingService == null) {
            geocodingService = geocodingMap.get("google");
        }
        return geocodingService;
    }

    public List<AddressDetailsResponse> getReverseHistory() {
        return history;

    }

    private void addToHistory(AddressDetailsResponse info) {
        history.add(info);
        if (history.size()>10) {
            history.remove(0);
        }
    }

}
