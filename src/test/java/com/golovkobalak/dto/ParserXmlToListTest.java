package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserXmlToListTest {

    private List<CurrencyRate> expected;
    private InputStream stream;

    @Before
    public void setUp() throws Exception {
        stream = IOUtils.toInputStream("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<root>\n" +
                "    <CurrencyRate>\n" +
                "        <name>USD</name>\n" +
                "        <buy>25.9</buy>\n" +
                "        <sell>26.1</sell>\n" +
                "    </CurrencyRate>\n" +
                "    <CurrencyRate>\n" +
                "        <name>EUR</name>\n" +
                "        <buy>30</buy>\n" +
                "        <sell>31</sell>\n" +
                "    </CurrencyRate>\n" +
                "    <CurrencyRate>\n" +
                "        <name>CHF</name>\n" +
                "        <buy>26</buy>\n" +
                "    </CurrencyRate>\n" +
                "</root>", Charset.defaultCharset());
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
        List<CurrencyRate> actual = ParserXmlToList.getListFrom(stream);

        assertEquals(expected, actual);

    }
}