package cz.marianjanik.mysqljpa.jsonmysql;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class VatResponse {

    @JsonProperty(value = "last_update")
    private String lastUpdated;
    private String disclaimer;
    private Map<String, CountryVat> rates = new HashMap();

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, CountryVat> getRates() {
        return rates;
    }

    public void setRates(Map<String, CountryVat> rates) {
        this.rates = rates;
    }
}