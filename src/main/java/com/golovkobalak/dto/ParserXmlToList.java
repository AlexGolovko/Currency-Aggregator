package com.golovkobalak.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.golovkobalak.model.CurrencyRate;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ParserXmlToList {

    //@JacksonXmlElementWrapper(useWrapping = false)
    private static List<CurrencyRate>rates;

    public static List<CurrencyRate>getListFrom(InputStream stream){

        XmlMapper xmlMapper = new XmlMapper();


        try {
           rates=xmlMapper.readValue(stream, WrapperCureencyRate.class).getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rates;
    }
}
