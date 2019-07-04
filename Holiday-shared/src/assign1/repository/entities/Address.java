/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Karth
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable{
    
    private String streetAddress;
    private String city;
    private String country;
    private String postcode;

    public Address() {
    }

    public Address(String streetAddress, String city, String country, 
            String postcode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    @Column(name = "street_address")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address{" + "streetAddress=" + streetAddress + ", city=" + 
                city + ", country=" + country + ", postcode=" + postcode + '}';
    }
    
}

