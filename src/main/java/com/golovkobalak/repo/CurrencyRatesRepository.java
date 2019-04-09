package com.golovkobalak.repo;

import com.golovkobalak.model.Bank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.golovkobalak.model.CurrencyRate;

import java.util.List;
import java.util.Optional;

public interface CurrencyRatesRepository extends CrudRepository<CurrencyRate, Long> {
    @Override
    Optional<CurrencyRate> findById(Long aLong);

    Optional<List<CurrencyRate>> findAllByName(String name);

    Optional<CurrencyRate> findCurrencyRateByNameAndBank_BankName(String name, String bank_bankName);

    Optional<CurrencyRate> findFirstByNameOrderByBuyPriceAsc(String name);

    
    Optional<CurrencyRate>  findFirstByNameOrderBySellPriceAsc(String name);




    @Query(value="SELECT Distinct d.name FROM CurrencyRate d   ")
    List<String> findAllUniqueCurrencyName();

    boolean existsByBank_BankName(String bankName);

    boolean deleteCurrencyRatesByBank_BankName(String bankName);

    boolean existsByName(String name);

    boolean existsCurrencyRateByBank_BankName(String bankName);
}
