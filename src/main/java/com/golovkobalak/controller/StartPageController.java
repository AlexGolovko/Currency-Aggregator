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

    @GetMapping
    public String getStartPage() {
        return "uploadForm";
    }

    @PostMapping(value = "/rates")
    public List<CurrencyRate> getRatesFromAllBanks(@RequestParam(name = "currency") String currency, @RequestParam("operType") String operationType, @RequestParam("sorting") String sorting) {
        if (!operationType.toUpperCase().equals("BUY") || !operationType.toUpperCase().equals("SELL")) return null;
        if (!currencyRatesRepository.existsByName(currency.toUpperCase())) return null;

        Optional<List<CurrencyRate>> resultOptional = currencyRatesRepository.findAllByName(currency.toUpperCase());
        if (!resultOptional.isPresent()) return null;
        List<CurrencyRate> result = resultOptional.get();
        List<Map<String, Double>> bankName2Price =new ArrayList<>();
        if (sorting != null) {
            if (operationType.toUpperCase().equals("BUY")) {
                result.sort(Comparator.comparingDouble(CurrencyRate::getBuyPrice));
            } else {
                result.sort(Comparator.comparing(CurrencyRate::getSellPrice));
            }

            if (sorting.toUpperCase().equals("ASC")) {

                result.forEach((rate)->{bankName2Price.add(new HashMap<>().put(rate.getBank().getBankName(),rate.getBuyPrice()))});
            }

        } else {

        }


        Iterator<CurrencyRate> iterator = result.get().iterator();
        Map<Bank, Double> bankDoubleMap = new HashMap<>();
        iterator.forEachRemaining(rate -> bankDoubleMap.put(rate.getBank(), rate.));
        return null;
    }

}
