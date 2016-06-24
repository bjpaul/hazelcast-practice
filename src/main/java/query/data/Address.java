package query.data;

import java.io.Serializable;

/**
 * Created by bijoy on 24/6/16.
 */
public class Address implements Serializable {
    final static long serialVersionUID = 1l;

    private int zipCode;
    private String city;

    public Address(){}

    public Address(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode=" + zipCode +
                ", city='" + city + '\'' +
                '}';
    }
}
