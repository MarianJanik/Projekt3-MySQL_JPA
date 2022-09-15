package cz.marianjanik.mysql_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(path = "/vat/abb/{abbreviation}")
    public Optional<Rate> getByAbbreviation(@PathVariable String abbreviation) {
        return ratesRepository.findById(abbreviation);
    }

    @GetMapping(path = "/vat/country/{country}")
    public Rate getByCountry(@PathVariable String country) {
        for (Rate item:ratesRepository.findAll()) {
            if (item.getCountry().equals(country)) return item;
        }
        return null;
    }

    @GetMapping(path = "/vat/larger/{standardRate}")
    public List<Rate> getAllByMinStandardRate(@PathVariable double standardRate) {
        List<Rate>resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            if (item.getStandardRate() > standardRate) resultList.add(item);
        }
        return resultList;
    }

    @GetMapping(path = "/vat/less/{standardRate}")
    public List<Rate> getAllByMaxStandardRate(@PathVariable double standardRate) {
        List<Rate>resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            if (item.getStandardRate() < standardRate) resultList.add(item);
        }
        return resultList;
    }

    @GetMapping(path = "/vat/interval/{min}/{max}")
    public List<Rate> getAllInIntervalStandardRate(@PathVariable double min,@PathVariable double max) {
        List<Rate>resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            if ((item.getStandardRate() < max) && (item.getStandardRate() > min)) resultList.add(item);
        }
        return resultList;
    }
}