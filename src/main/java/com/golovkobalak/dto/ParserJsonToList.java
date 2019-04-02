package com.golovkobalak.dto;

import com.golovkobalak.model.CurrencyRate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ParserJsonToList {
    private static Type listType = new TypeToken<ArrayList<CurrencyRate>>() {
    }.getType();

    public static List<CurrencyRate> getListFrom(InputStream stream) {

        try {
            return new Gson().fromJson(String.valueOf(IOUtils.toCharArray(stream, StandardCharsets.UTF_8)),
                    listType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}
