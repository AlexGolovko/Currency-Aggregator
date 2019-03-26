package com.golovkobalak.repo;

import com.golovkobalak.model.CurrencyRate;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CurrencyRatesRepository extends PagingAndSortingRepository<CurrencyRate,Long> {


}
