package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ParserCsvToList {

    public static List<CurrencyRate> getListFrom(InputStream stream) {


        return new CsvToBeanBuilder(new InputStreamReader(stream))
                .withType(CurrencyRate.class)
                .build()
                .parse();
    }
}
