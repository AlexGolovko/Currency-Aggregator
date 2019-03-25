package com.golovkobalak.repo;

import com.golovkobalak.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface CurrencyRatesRepository extends PagingAndSortingRepository<CurrencyRates,Long> {


}
