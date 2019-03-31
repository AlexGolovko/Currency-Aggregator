package com.golovkobalak.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.golovkobalak.model.CurrencyRate;

public interface CurrencyRatesRepository extends PagingAndSortingRepository<CurrencyRate, Long> {


}
