package cz.marianjanik.mysql_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path= "/vat/min/{min}")
    public String getCountBiggerMin(@PathVariable int min) {
        int count = 0;
        for (Rate item:ratesRepository.findAll()) {
            if (item.getStandardRate()>min) count++;
        }
        return "Počet všech zemí s DPH větším než " + min
                + " je " + count + ".";
    }

    @GetMapping(path = "/vat/count/{count}")
    public List<Rate> getMaxValue(@PathVariable int count) {
        TreeSet<Double> vatSet = new TreeSet<>();
        List<Rate> resultList = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            vatSet.add(item.getStandardRate());
        }
        vatSet = (TreeSet<Double>) vatSet.descendingSet();
        int counter = 0;
        Double min = null;
        for (Double item:vatSet) {
            counter++;
            if (counter == count) min = item;
        }
        for (Rate item: ratesRepository.findAll()) {
            if (item.getStandardRate()>=min) resultList.add(item);
        }
        Collections.sort(resultList, new StandardRateComparator().reversed());
        return resultList;
    }

    @GetMapping(path = "/vat/interval/info/{min}/{max}")
    public String getIntervalInfo(@PathVariable double min,@PathVariable double max) {
        int count = 0;
        List<String> resultSet = new ArrayList<>();
        for (Rate item:ratesRepository.findAll()) {
            if ((item.getStandardRate() < max) && (item.getStandardRate() > min)) {
                resultSet.add(item.getCountry());
                count++;
            }
        }
        Collections.sort(resultSet);
        String result = "Počet státu v intervalu od " + min + "% do " + max + "% je " + count + ".";
        result += lineSeparator() + "Státy: " + lineSeparator() + resultSet;
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
            if (item.getReducedRate() != 0) {
                sumOfReducedRate += item.getReducedRate();
                countOfReduceRate++;
            }
            if (item.getParkingRate() != 0) {
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