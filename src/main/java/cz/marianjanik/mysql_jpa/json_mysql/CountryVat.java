package cz.marianjanik.mysql_jpa.json_mysql;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryVat {

    private String country;
    @JsonProperty(value = "standard_rate")
    private double standardRate;
    @JsonProperty(value = "reduced_rate")
    private double reducedRate;
    @JsonProperty(value = "reduced_rate_alt")
    private double reducedRateAlt;
    @JsonProperty(value = "super_reduced_rate")
    private double superReducedRate;
    @JsonProperty(value = "parking_rate")
    private double parkingRate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(String standardRate) {
        this.standardRate = (standardRate.equals("false") ? 0 : Double.valueOf(standardRate));
    }

    public double getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(String reducedRate) {
        this.reducedRate = (reducedRate.equals("false") ? 0 : Double.valueOf(reducedRate));
    }

    public double getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(String reducedRateAlt) {
        this.reducedRateAlt = (reducedRateAlt.equals("false") ? 0 : Double.valueOf(reducedRateAlt));
    }

    public double getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(String superReducedRate) {
        this.superReducedRate = (superReducedRate.equals("false") ? 0 : Double.valueOf(superReducedRate));
    }

    public double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(String parkingRate) {
        this.parkingRate = (parkingRate.equals("false") ? 0 : Double.valueOf(parkingRate));
    }
}
