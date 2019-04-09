package com.golovkobalak.dto;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.golovkobalak.model.CurrencyRate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ParserXmlToList {


    private static List<CurrencyRate> rates;

    public static List<CurrencyRate> getListFrom(InputStream stream) {

        XmlMapper xmlMapper = new XmlMapper();


        try {
            rates = xmlMapper.readValue(stream, WrapperCurrencyRate.class).getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rates;
    }
}
