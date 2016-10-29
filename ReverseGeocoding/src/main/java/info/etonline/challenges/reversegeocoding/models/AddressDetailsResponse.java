package info.etonline.challenges.reversegeocoding.models;

import java.util.Date;

/**
 * Created by erich on 10/29/2016.
 */
public class AddressDetailsResponse {
    private String latitude;
    private String longitude;
    private String address;
    private Date requestDate;

    public AddressDetailsResponse(String latitude, String longitude, String address, Date datetime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.requestDate = datetime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
