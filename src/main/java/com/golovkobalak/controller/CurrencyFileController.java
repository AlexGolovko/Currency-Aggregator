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

import javax.validation.constraints.NotNull;
import javax.xml.ws.FaultAction;
import java.util.List;

@RestController
@RequestMapping("/CurrencyAggregator/upload")
public class CurrencyFileController {
    private static final Logger logger = LogManager.getLogger(CurrencyFileController.class);

    private final CurrencyRatesRepository currencyRatesRepository;


    @Autowired
    public CurrencyFileController(CurrencyRatesRepository currencyRatesRepository) {
        this.currencyRatesRepository = currencyRatesRepository;

    }

    @GetMapping
    public String getStartPage() {
        return "GET ";
    }

    @PostMapping
    public HttpStatus postCurrencyFile(@RequestParam(value = "file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            logger.error("File is Empty");
            return HttpStatus.BAD_REQUEST;
        }
        final String bankName = uploadFile.getOriginalFilename().substring(0, uploadFile.getOriginalFilename().indexOf("."));
        if(currencyRatesRepository.existsCurrencyRateByBank_BankName(bankName))return HttpStatus.BAD_REQUEST;

        List<CurrencyRate> rates = CurrencyRateDTO.convertUploadFileToObj(uploadFile);
        logger.error(rates.toString());
        currencyRatesRepository.saveAll(rates);
        return HttpStatus.OK;
    }

}
