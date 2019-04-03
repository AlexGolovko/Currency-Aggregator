package com.golovkobalak.dto;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.golovkobalak.model.CurrencyRate;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.io.IOUtils;
import org.h2.tools.Csv;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserCsvToList {

    public static List<CurrencyRate> getListFrom(InputStream stream) {

      /*  //
        MappingIterator<CurrencyRate> personIter = null;
        try {
            personIter = new CsvMapper().readerWithTypedSchemaFor(CurrencyRate.class).readValues(stream);
            return personIter.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
        //

        //
       *//* CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CurrencyRate.class).withHeader();
        try {
            MappingIterator<CurrencyRate> it = mapper.readerFor(CurrencyRate.class).with(schema)
                    .readValues(stream);
           return it.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;*//*
        //
*/

        return new CsvToBeanBuilder(new InputStreamReader(stream))
                .withType(CurrencyRate.class)
                .build()
                .parse();
    }
}
