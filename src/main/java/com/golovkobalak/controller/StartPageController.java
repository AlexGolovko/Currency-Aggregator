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

@RestController
@RequestMapping("/CurrencyAggregator")
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
    public Map<String, Double> getRatesFromAllBanks(@RequestParam(name = "currency") String currency, @RequestParam("operType") String operationType, @RequestParam(value = "sorting", required = false) String sorting) {
        if (!isPresentData(currency, operationType))
            return Collections.EMPTY_MAP;
        Optional<List<CurrencyRate>> resultOptional = currencyRatesRepository.findAllByName(currency.toUpperCase());
        List<CurrencyRate> result = resultOptional.get();
        if (sorting==null)
            return getNotSortedRates(result, operationType);
        if (sorting.toUpperCase().equals("ASC"))
            return getSortedRatesOrderByAsc(result, operationType);
        if (sorting.toUpperCase().equals("DESC"))
            return getSortedRatesOrderByDesc(result, operationType);
        return null;

    }

    @PutMapping(value = "/rates")
    public CurrencyRate updateCurrencyRateOrSetBan(@RequestParam(name = "bank") String bankName,
                                                   @RequestParam(name = "currency") String currency,
                                                   @RequestParam("operType") String operationType,
                                                   @RequestParam(value = "price", required = false) double price,
                                                   @RequestParam(value = "allowOper", required = false) String allowOper) {
        Optional<CurrencyRate> currencyRateOptional = currencyRatesRepository.findCurrencyRateByNameAndBank_BankName(currency, bankName);
        if (!currencyRateOptional.isPresent()) return null;
        CurrencyRate currencyRate = currencyRateOptional.get();
        if (operationType.toUpperCase().equals("BUY"))
            currencyRate.setBuyPrice(price);
        if (operationType.toUpperCase().equals("SELL"))
            currencyRate.setSellPrice(price);
        if (allowOper != null && (allowOper.toUpperCase().equals("TRUE") || allowOper.toUpperCase().equals("FALSE"))) {
            if (operationType.toUpperCase().equals("BUY")) currencyRate.setAllowBuy(Boolean.getBoolean(allowOper));
            else currencyRate.setAllowSell(Boolean.getBoolean(allowOper));
        }
        if (currencyRatesRepository.save(currencyRate) != null)
            return currencyRate;
        return null;
    }

    @DeleteMapping(value = "/rates")
    public HttpStatus deleteAllCurrencyRatesForBank(@RequestParam(value = "bank") String bankName) {
        if (!currencyRatesRepository.existsByBank_BankName(bankName)) return HttpStatus.BAD_REQUEST;
        if (currencyRatesRepository.deleteCurrencyRatesByBank_BankName(bankName)) return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
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
        if (!(operationType.toUpperCase().equals("BUY") || operationType.toUpperCase().equals("SELL"))) return false;
        if (!currencyRatesRepository.existsByName(currency.toUpperCase())) return false;
        return true;
    }


}
