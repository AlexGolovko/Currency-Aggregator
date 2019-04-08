package com.golovkobalak.controller;

import com.golovkobalak.dto.CurrencyRateDTO;
import com.golovkobalak.model.CurrencyRate;
import com.golovkobalak.repo.CurrencyRatesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/CurrencyAggregator/upload")
public class UploadController {
    private static final Logger logger = LogManager.getLogger(UploadController.class);

    private final CurrencyRatesRepository currencyRatesRepository;


    @Autowired
    public UploadController(CurrencyRatesRepository currencyRatesRepository) {
        this.currencyRatesRepository = currencyRatesRepository;

    }


    @PostMapping
    public HttpStatus postCurrencyFile(@RequestParam(value = "file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            logger.error("File is Empty");
            return HttpStatus.BAD_REQUEST;
        }
        final String bankName = uploadFile.getOriginalFilename().substring(0, uploadFile.getOriginalFilename().indexOf("."));
        if (currencyRatesRepository.existsCurrencyRateByBank_BankName(bankName)) return HttpStatus.BAD_REQUEST;

        List<CurrencyRate> rates = CurrencyRateDTO.convertUploadFileToObj(uploadFile);
        for (CurrencyRate rate : rates) {
            rate.setAllowBuy(true);
            rate.setAllowSell(true);
        }


        logger.error(rates.toString());
        currencyRatesRepository.saveAll(rates);
        return HttpStatus.OK;
    }

}
