package cz.marianjanik.mysqljpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    private String abbreviation;
    @Column
    private String country;
    @Column(name = "standard_rate")
    private double standardRate;
    @Column(name = "reduced_rate")
    private double reducedRate;
    @Column(name = "reduced_rate_alt")
    private double reducedRateAlt;
    @Column(name = "super_reduced_rate")
    private double superReducedRate;
    @Column(name = "parking_rate")
    private double parkingRate;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(double standardRate) {
        this.standardRate = standardRate;
    }

    public double getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(double reducedRate) {
        this.reducedRate = reducedRate;
    }

    public double getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(double reducedRateAlt) {
        this.reducedRateAlt = reducedRateAlt;
    }

    public double getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(double superReducedRate) {
        this.superReducedRate = superReducedRate;
    }

    public double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(double parkingRate) {
        this.parkingRate = parkingRate;
    }
}