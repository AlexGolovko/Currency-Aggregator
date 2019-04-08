package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserCsvToListTest {
    private InputStream stream;
    private List<CurrencyRate> expected = new ArrayList<CurrencyRate>();

    @Before
    public void setUp() throws Exception {


        stream = IOUtils.toInputStream("name,buy,sell\n" +
                "USD,25.9,26.1\n" +
                "EUR,30,31\n" +
                "CHF,26,\n", Charset.defaultCharset());
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"USD\",\n" +
                "    \"buy\": 25.9,\n" +
                "    \"sell\": 26.1\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"EUR\",\n" +
                "    \"buy\": 30.0,\n" +
                "    \"sell\": 31.0\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"CHF\",\n" +
                "    \"buy\": 26.0\n" +
                "  }\n" +
                "]\n";
        Type listType = new TypeToken<ArrayList<CurrencyRate>>() {
        }.getType();
        expected = new Gson().fromJson(json, listType);

    }

    @Test
    public void getListFrom() {

        List<CurrencyRate> actual = ParserCsvToList.getListFrom(stream);
        actual.forEach(System.out::println);
        expected.forEach(System.out::println);
        assertEquals(expected, actual);

    }

    @Test
    public void getListFromFile() {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get("C:\\Golovko\\Currency-Aggregator\\src\\test\\testResourse\\oshad.csv")));
            System.out.println(IOUtils.toString(bufferedInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CurrencyRate> actual = ParserCsvToList.getListFrom(bufferedInputStream);
        System.out.println("ACTUAL");
        actual.forEach(System.out::println);
        System.out.println("EXPECTED");
        expected.forEach(System.out::println);
        assertEquals(expected, actual);
    }
}