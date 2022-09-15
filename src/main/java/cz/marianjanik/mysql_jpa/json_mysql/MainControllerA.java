package cz.marianjanik.mysql_jpa.json_mysql;

import cz.marianjanik.mysql_jpa.Rate;
import cz.marianjanik.mysql_jpa.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Projekt 3 - část A/
 * Převedení vybraných dat (JSON) z URL adresy do databáze (MySQL).
 */

@RestController
public class MainControllerA {

    @Autowired
    RatesRepository ratesRepository;

    @GetMapping(path = "/vat/test1")
    public String test1() {
        return "Kontrola spojení.";
    }

    @GetMapping(path = "/vat/reset")
    public String reset() {
        CallJson call = new CallJson();
        String jsonText = call.callApi();
        VatResponse response = call.mapToObject(jsonText);
        addRate(response);
        return jsonText;
    }

    private void addRate(VatResponse response) {
        for (Map.Entry<String,CountryVat> entry : response.getRates().entrySet()) {
            Rate rate = new Rate();
            rate.setAbbreviation(entry.getKey());
            rate.setCountry(entry.getValue().getCountry());
            rate.setStandardRate(entry.getValue().getStandardRate());
            rate.setReducedRate(entry.getValue().getReducedRate());
            rate.setReducedRateAlt(entry.getValue().getReducedRateAlt());
            rate.setSuperReducedRate(entry.getValue().getSuperReducedRate());
            rate.setParkingRate(entry.getValue().getParkingRate());
            ratesRepository.save(rate);
        }
    }
}
