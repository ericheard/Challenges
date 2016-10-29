package info.etonline.challenges.reversegeocoding.controllers;

import info.etonline.challenges.reversegeocoding.models.AddressResponse;
import info.etonline.challenges.reversegeocoding.models.AddressDetailsResponse;
import info.etonline.challenges.reversegeocoding.services.GeocodingDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by erich on 10/26/2016.
 */

@RestController
@RequestMapping("/geocoding/reverse")
public class ReverseGeocodingController {
    @Autowired
    private GeocodingDelegateService geoService;

    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    AddressResponse query(@RequestParam("lat") String latitude, @RequestParam("long") String longitude,
                          @RequestParam("provider") Optional<String> provider) {

        if (!provider.isPresent()) {
            return geoService.getReverseGeocodingViaCoordinates(null, latitude, longitude);
        } else {
            return geoService.getReverseGeocodingViaCoordinates(provider.get(), latitude, longitude);
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<AddressDetailsResponse> history() {
        return geoService.getReverseHistory();
    }
}
