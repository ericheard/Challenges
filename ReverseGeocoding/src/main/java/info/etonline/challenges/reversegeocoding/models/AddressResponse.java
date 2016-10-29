package info.etonline.challenges.reversegeocoding.models;

/**
 * Created by erich on 10/29/2016.
 */
public class AddressResponse {
    private String address;

    public AddressResponse(String address) {
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
}
