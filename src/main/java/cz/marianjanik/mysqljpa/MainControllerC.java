package cz.marianjanik.mysqljpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import static java.lang.System.lineSeparator;

/**
 * Projekt 3 - část C/
 * Vypočet základních statistických údajů z databáze.
 */

@RestController
public class MainControllerC {

    @Autowired
    RatesRepository ratesRepository;

    @GetMapping(path= "/vat/count")
    public String getCountRecord() {
        return "Počet všech zemí v databázi je " + String.valueOf(ratesRepository.count()) + ".";
    }

    @GetMapping(path= "/vat/min")
    public String getCountBiggerMin(@RequestParam int min) {
        List<Rate> rateList = ratesRepository.findRateByStandardRateGreaterThanOrderByStandardRateDesc(min);
        return "Počet všech zemí s DPH větším než " + min
                + " je " + rateList.size() + ".";
    }

    @GetMapping(path = "/vat/count2")
    public List<Rate> getMaxValue(@RequestParam int count) {
        TreeSet<Double> vatSet = new TreeSet<>();
        List<Rate> resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            vatSet.add(item.getStandardRate());
        }
        vatSet = (TreeSet<Double>) vatSet.descendingSet();
        int counter = 0;
        Double min = null;
        for (Double item:vatSet) {
            if (counter == count) min = item;
            counter++;
        }
        resultList = ratesRepository.findRateByStandardRateGreaterThanOrderByStandardRateDesc(min);
        return resultList;
    }

    @GetMapping(path = "/vat/interval/info")
    public String getIntervalInfo(@RequestParam double min,@RequestParam double max) {
        List<Rate> resultRate = ratesRepository.findRateByStandardRateBetweenOrderByCountryAsc(min,max);
        List<String> resultList = new ArrayList<>();
        for (Rate item:resultRate) {
            resultList.add(item.getCountry());
        }
        String result = "Počet státu v intervalu od " + min + "% do " + max + "% je " + resultRate.size() + ".";
        result += lineSeparator() + "Státy: " + lineSeparator() + resultList;
        return result;
    }

    @GetMapping(path = "/vat/info/avgrates")
    public String getInfoAvg() {
        double sumOfStandardRate = 0;
        double sumOfReducedRate = 0;
        int countOfReduceRate = 0;
        double sumOfParkingRate = 0;
        int countOfParkingRate = 0;
        for (Rate item: ratesRepository.findAll()) {
            sumOfStandardRate += item.getStandardRate();
            if (item.getReducedRate() > 0) {
                sumOfReducedRate += item.getReducedRate();
                countOfReduceRate++;
            }
            if (item.getParkingRate() > 0) {
                sumOfParkingRate += item.getParkingRate();
                countOfParkingRate++;
            }
        }
        String result = "Průměr základní sazby DPH všech státu v databázi je " + String.format("%.2f", sumOfStandardRate/ratesRepository.count()) + "%," + lineSeparator()
                + "průměr snížené sazby je " + String.format("%.2f", sumOfReducedRate/countOfReduceRate) + "%," + lineSeparator()
                + "průměr speciální sazby parking je " + String.format("%.2f", sumOfParkingRate/countOfParkingRate) + "%.";
        return result;
    }
}