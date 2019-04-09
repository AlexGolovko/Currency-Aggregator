package com.golovkobalak.controller;

import com.golovkobalak.dto.CurrencyReportDTO;
import com.golovkobalak.model.CurrencyRate;
import com.golovkobalak.repo.CurrencyRatesRepository;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController(value = "/CurrencyAggregator/report")
public class ReportController {

    private final CurrencyRatesRepository currencyRatesRepository;
    private final List<CurrencyReportDTO> report;

    @Autowired
    public ReportController(CurrencyRatesRepository currencyRatesRepository, CurrencyReportDTO report, List<CurrencyReportDTO> report1) {
        this.currencyRatesRepository = currencyRatesRepository;

        this.report = report1;
    }

    @GetMapping
    public List<CurrencyReportDTO> getReport() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        List<String> resultOptional = currencyRatesRepository.findAllUniqueCurrencyName();
        if (resultOptional == null) return Collections.emptyList();

        List<String> currNames = resultOptional;
        for (String name : currNames) {
            CurrencyReportDTO reportDTO = new CurrencyReportDTO();
            reportDTO.setCurrencyName(name);
            Optional<CurrencyRate> currencyRateMaxBuy = currencyRatesRepository.findFirstByNameOrderByBuyPriceAsc(name);
            Optional<CurrencyRate> currencyRateMinSell = currencyRatesRepository.findFirstByNameOrderBySellPriceDesc(name);
            if (currencyRateMaxBuy.isPresent()) {
                reportDTO.setBuyBank(currencyRateMaxBuy.get().getBank().getBankName());
                reportDTO.setBuyRate(currencyRateMaxBuy.get().getBuyPrice());
            }
            if (currencyRateMinSell.isPresent()) {
                reportDTO.setSellBank(currencyRateMinSell.get().getBank().getBankName());
                reportDTO.setSellRate(currencyRateMinSell.get().getSellPrice());
            }
            report.add(reportDTO);
        }
        return report;

    }
}
