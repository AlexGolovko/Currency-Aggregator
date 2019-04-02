package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyRateDTOTest {


    @Test
    public void convertUploadFileToObj() {
        try {
            String jsonFile = Arrays.toString(Files.readAllBytes(Paths.get("Monobank.json")));
            String csvFile = Arrays.toString(Files.readAllBytes(Paths.get("oshad.csv")));
            String xmlFile = Arrays.toString(Files.readAllBytes(Paths.get("Privat.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}