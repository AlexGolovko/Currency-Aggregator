package com.golovkobalak.repo;

import com.golovkobalak.model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.golovkobalak.model.CurrencyRate;

import java.util.List;
import java.util.Optional;

public interface CurrencyRatesRepository extends CrudRepository<CurrencyRate, Long> {
    @Override
    Optional<CurrencyRate> findById(Long aLong);

    Optional<List<CurrencyRate>>findAllByName(String name);
    Optional<List<CurrencyRate>>findAllByNameOrderByBuyPriceAsc(String name);
    Optional<List<CurrencyRate>>findAllByNameOrderByBuyPriceDesc(String name);

    Optional<List<CurrencyRate>>findAllByNameOrderBySellPriceAsc(String name);
    Optional<List<CurrencyRate>>findAllByNameOrderBySellPriceDesc(String name);


    boolean existsByName(String name);

    boolean existsCurrencyRateByBank_BankName(String bankName);
}
