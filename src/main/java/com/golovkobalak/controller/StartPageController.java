package com.golovkobalak.controller;

import com.golovkobalak.model.Bank;
import com.golovkobalak.model.CurrencyRate;
import com.golovkobalak.repo.CurrencyRatesRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller("/CurrencyAggregator")
public class StartPageController {

    private final CurrencyRatesRepository currencyRatesRepository;


    @Autowired
    public StartPageController(CurrencyRatesRepository currencyRatesRepository) {
        this.currencyRatesRepository = currencyRatesRepository;

    }

    @GetMapping(value = "/")
    public String getStartPage() {
        return "uploadForm";
    }

    @GetMapping(value = "/rates")
    public Map<String, Double> getRatesFromAllBanks(@RequestParam(name = "currency") String currency, @RequestParam("operType") String operationType, @RequestParam("sorting") String sorting) {
        isPresentData(currency, operationType);
        Optional<List<CurrencyRate>> resultOptional = currencyRatesRepository.findAllByName(currency.toUpperCase());
        List<CurrencyRate> result = resultOptional.get();
        if (sorting.isEmpty())
            return getNotSortedRates(result, operationType);
        if (sorting.toUpperCase().equals("ASC"))
            return getSortedRatesOrderByAsc(result, operationType);
        if (sorting.toUpperCase().equals("DESC"))
            return getSortedRatesOrderByDesc(result, operationType);
        return null;
       /* LinkedHashMap<String, Double> bankName2Price = new LinkedHashMap<>();
        if (sorting != null) {
            if (operationType.toUpperCase().equals("BUY")) {
                result.sort(Comparator.comparingDouble(CurrencyRate::getBuyPrice));
            } else {
                result.sort(Comparator.comparingDouble(CurrencyRate::getSellPrice));
            }

            if (sorting.toUpperCase().equals("ASC")) {

                result.forEach((rate) -> {
                    bankName2Price.put(rate.getBank().getBankName(), rate.getBuyPrice());
                });
            }

        } else {

        }


        Iterator<CurrencyRate> iterator = result.get().iterator();
        Map<Bank, Double> bankDoubleMap = new HashMap<>();
        iterator.forEachRemaining(rate -> bankDoubleMap.put(rate.getBank(), rate.));
        return null;*/
    }

    private Map<String, Double> getSortedRatesOrderByDesc(List<CurrencyRate> currencyRates, String operationType) {
        Map<String, Double> result = new TreeMap<>();
        if (operationType.toUpperCase().equals("BUY")) {
            currencyRates.sort(Comparator.comparing(CurrencyRate::getBuyPrice).reversed());
            currencyRates.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getBuyPrice()));
        } else {
            currencyRates.sort(Comparator.comparing(CurrencyRate::getSellPrice).reversed());
            currencyRates.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getSellPrice()));
        }
        return result;
    }

    private Map<String, Double> getSortedRatesOrderByAsc(List<CurrencyRate> currencyRates, String operationType) {
        Map<String, Double> result = new TreeMap<>();
        if (operationType.toUpperCase().equals("BUY")) {
            currencyRates.sort(Comparator.comparing(CurrencyRate::getBuyPrice));
            currencyRates.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getBuyPrice()));
        } else {
            currencyRates.sort(Comparator.comparing(CurrencyRate::getSellPrice));
            currencyRates.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getSellPrice()));
        }
        return result;
    }

    private Map<String, Double> getNotSortedRates(List<CurrencyRate> currencyRateList, String operationType) {
        Map<String, Double> result = new HashMap<>();
        if (operationType.toUpperCase().equals("BUY")) {
            currencyRateList.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getBuyPrice()));
        } else {
            currencyRateList.forEach(rate -> result.put(rate.getBank().getBankName(), rate.getSellPrice()));
        }
        return result;
    }

    private boolean isPresentData(String currency, String operationType) {
        if (!operationType.toUpperCase().equals("BUY") || !operationType.toUpperCase().equals("SELL")) return false;
        if (!currencyRatesRepository.existsByName(currency.toUpperCase())) return false;
        return true;
    }


}
