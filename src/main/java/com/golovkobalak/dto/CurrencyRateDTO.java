package com.golovkobalak.dto;

import com.golovkobalak.model.Bank;
import com.golovkobalak.model.CurrencyRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CurrencyRateDTO {


    private static final Logger logger = LogManager.getLogger(CurrencyRateDTO.class);
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    private static final Gson gson = gsonBuilder.create();


    public static List<CurrencyRate> convertUploadFileToObj(MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("File is Empty");
        }
        final String bankName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        final String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);

        List<CurrencyRate> rates = null;
        try (InputStream stream = file.getInputStream()) {
            switch (fileType) {

                case "json":
                    rates = ParserJsonToList.getListFrom(stream);
                    break;
                case "xml":
                    rates = ParserXmlToList.getListFrom(stream);
                    break;
                case "csv":
                    rates = ParserCsvToList.getListFrom(stream);
                    break;
                default:
                    throw new FileUploadException();
            }
        } catch (IOException | FileUploadException e) {
            logger.error(e.getMessage());
        }
        Bank bank = new Bank(bankName);
        rates.forEach(rate -> rate.setBank(bank));
        return rates;

    }


}