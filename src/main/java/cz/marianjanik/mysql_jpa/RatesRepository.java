package cz.marianjanik.mysql_jpa;

import org.springframework.data.repository.CrudRepository;

public interface RatesRepository extends CrudRepository<Rate, String> {
}
