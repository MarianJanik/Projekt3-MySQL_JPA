package cz.marianjanik.mysqljpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainControllerBUpdate {

    /**
     * Projekt 3 - část B Update
     * Vytvoření rozhraní (REST API) s daty z databáze podle požadavků.
     * Úpravy v databázi (Delete, Update, Insert).
     */

    @Autowired
    RatesRepository ratesRepository;

    @DeleteMapping(path = "/vat/delete")
    public String deleteByAbbreviation(@RequestParam String abbreviation) {
        String result = "Hledaný záznam se zkratkou " + abbreviation + " nebyl nalezen.";
        if (ratesRepository.findById(abbreviation).isPresent()) {
            ratesRepository.deleteById(abbreviation);
            result = "Hledaný záznam se zkratkou " + abbreviation + " byl smazán.";
        }
        return result;
    }

    @DeleteMapping(path = "/vat/deletebycountry")
    public String deleteByCountry(@RequestParam String country) {
        String result = "Záznam státu " + country + " nebyl nalezen.";
        List<Rate> deleteRates = ratesRepository.findRateDistinctByCountry(country);
        if (!deleteRates.isEmpty()) {
            for (Rate item:deleteRates) {
                ratesRepository.delete(item);
            }
            result = "Záznam státu " + country + " byl smazán.";
        }
        return result;
    }

    @PostMapping(path = "/vat/update")
    public String updateCountry(@RequestBody Rate updateCountry) {
        String result = "Záznam se zkratkou " + updateCountry.getAbbreviation() + " nebyl nalezen.";
        if (ratesRepository.findById(updateCountry.getAbbreviation()).isPresent()) {
            saveCountry(updateCountry);
            result = "Záznam byl upraven.";
        }
        return result;
    }

    @PutMapping(path = "/vat/new")
    public String newCountry(@RequestBody Rate newCountry) {
        String result = "Záznam byl úspěšně zapsán.";
        if (ratesRepository.findById(newCountry.getAbbreviation()).isPresent()) {
            result = "Záznam nebyl zapsán. Záznam se zkratkou " + newCountry.getAbbreviation() + " již existuje. " +
                    "Zvolte záznam s jinou zkratkou státu.";
        } else saveCountry(newCountry);
        return result;
    }

    private void saveCountry(Rate newCountry) {
        Rate saveCountry = new Rate();
        saveCountry.setAbbreviation(newCountry.getAbbreviation());
        saveCountry.setCountry(newCountry.getCountry());
        saveCountry.setStandardRate(newCountry.getStandardRate());
        saveCountry.setReducedRate(newCountry.getReducedRate());
        saveCountry.setReducedRateAlt(newCountry.getReducedRateAlt());
        saveCountry.setSuperReducedRate(newCountry.getSuperReducedRate());
        saveCountry.setParkingRate(newCountry.getParkingRate());
        ratesRepository.save(saveCountry);
    }
}