package com.golovkobalak.controller;

import com.golovkobalak.dto.CurrencyRatesDTO;
import com.golovkobalak.model.CurrencyRates;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/CurrencyAggregator/upload")
public class CurrencyFileController {
    private static final Logger logger = LogManager.getLogger(CurrencyFileController.class);


    @GetMapping
    public String getStartPage() {

        return "GET ";
    }

    @PostMapping
    public void postCurrencyFile(@RequestParam(value = "file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty())
            logger.error("File is Empty");
        final String fileName = uploadFile.getOriginalFilename();
        final String contentType = uploadFile.getContentType();
        CurrencyRates rates=CurrencyRatesDTO.convertUploadFileToObj(uploadFile);
        logger.error(rates.toString());
    }
}
