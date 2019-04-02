package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParserCsvToList {
    public static List<CurrencyRate> getListFrom(InputStream stream) {

        List<CurrencyRate> result;

        /*CsvToBean<CurrencyRate> csvToBean = new CsvToBeanBuilder(stream)
                .withType(.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();*/

        result = new CsvToBeanBuilder(new InputStreamReader(stream))
                .withType(CurrencyRate.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
        return result;
    }
}
