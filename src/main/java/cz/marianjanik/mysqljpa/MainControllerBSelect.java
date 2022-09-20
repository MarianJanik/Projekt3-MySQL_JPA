package cz.marianjanik.mysqljpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Projekt 3 - část B Select
 * Vytvoření rozhraní (REST API) s daty z databáze podle požadavků.
 * Výpisy z databáze (Select).
 */


@RestController
public class MainControllerBSelect {
    @Autowired
    RatesRepository ratesRepository;

    @GetMapping(path = "/vat/all")
    public Iterable<Rate> getAll() {
        return ratesRepository.findAll();
    }

    @GetMapping(path = "/vat/abbreviation")
    public Optional<Rate> getByAbbreviation(@RequestParam String abbreviation) {
        return ratesRepository.findById(abbreviation);
    }

    @GetMapping(path = "/vat/country")
    public List<Rate> getByCountry(@RequestParam String country) {
        return ratesRepository.findRateDistinctByCountry(country);
    }

    @GetMapping(path = "/vat/larger")
    public List<Rate> getAllOverMinStandardRate(@RequestParam double standardRate) {
        List<Rate>resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            if (item.getStandardRate() > standardRate) resultList.add(item);
        }
        return resultList;
    }
    @GetMapping(path = "/vat/less")
    public List<Rate> getAllUnderMaxStandardRate(@RequestParam double standardRate) {
        return ratesRepository.findRateByStandardRateLessThanOrderByStandardRateDesc(standardRate);
    }
    @GetMapping(path = "/vat/interval")
    public List<Rate> getAllInIntervalStandardRate(@RequestParam double min, @RequestParam double max) {
        return ratesRepository.findRateByStandardRateBetweenOrderByCountryAsc(min, max);
    }
}