package cz.marianjanik.mysqljpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RatesRepository extends CrudRepository<Rate, String> {
    List<Rate> findRateDistinctByCountry(String country);
    List<Rate> findRateByStandardRateLessThanOrderByStandardRateDesc(double standardRate);
    List<Rate> findRateByStandardRateGreaterThanOrderByStandardRateDesc(double min);
    List<Rate> findRateByStandardRateBetweenOrderByCountryAsc(double min,double max);
}
