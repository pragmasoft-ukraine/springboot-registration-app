package com.oliinyk.registration.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class CanadaUser extends User {

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
