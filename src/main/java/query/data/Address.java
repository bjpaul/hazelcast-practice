package query.data;

import java.io.Serializable;

/**
 * Created by bijoy on 24/6/16.
 */
public class Address implements Serializable , Comparable<Address>{
    final static long serialVersionUID = 1l;

    private Integer zipCode;
    private String city;

    public Address(){}

    public Address(Integer zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getZipCode() != address.getZipCode()) return false;
        return getCity().equals(address.getCity());

    }

    @Override
    public int hashCode() {
        int result = getZipCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }

    @Override
    public int compareTo(Address o) {
        if(this.equals(o)){
            return 0;
        }
        else if(this.getCity().compareTo(o.getCity()) == 0){
            return this.getZipCode().compareTo(o.getZipCode());
        }else {
            return this.getCity().compareTo(o.getCity());
        }
    }
}
