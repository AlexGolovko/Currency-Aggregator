package com.golovkobalak.dto;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.golovkobalak.model.CurrencyRates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.opencsv.CSVReader;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;


public class CurrencyRatesDTO {
    private static final Logger logger = LogManager.getLogger(CurrencyRatesDTO.class);
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    private static final Gson gson = gsonBuilder.create();

    public static CurrencyRates convertUploadFileToObj(MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("File is Empty");
        }
        final String bankName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        final String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
        CurrencyRates rates = null;
        try (InputStream stream = file.getInputStream()) {
            switch (fileType) {

                case "json":
                    rates = gson.fromJson(String.valueOf(IOUtils.toCharArray(stream, StandardCharsets.UTF_8)), CurrencyRates.class);
                    break;

                case "xml":
                    rates = convertXmlToObj(stream);
                    break;
                case "csv":
                    rates = convertCsvToObj(stream);
                    break;
                default:
                    throw new FileUploadException();
            }
        } catch (IOException | FileUploadException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        rates.setBankName(bankName);
        rates.setInitDate(new Date());
        return rates;


    }

    private static CurrencyRates convertXmlToObj(InputStream xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyRates.class);
            Unmarshaller unmarshaller = null;
            unmarshaller = jaxbContext.createUnmarshaller();
            return (CurrencyRates) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static CurrencyRates convertCsvToObj(InputStream cvsFile) {
        CSVReader csvReader = new CSVReader(new InputStreamReader(cvsFile));
        Iterator<String[]> iterator = csvReader.iterator();
        String[] headers = iterator.next();
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter)) {
            jsonGenerator.writeStartObject();
           //TODO Use reflection for add field name
            jsonGenerator.writeFieldName("CurrencyRate");
            jsonGenerator.writeStartArray();
            while (iterator.hasNext()) {
                jsonGenerator.writeStartObject();
                String[] values = iterator.next();
                int nbCells = Math.min(values.length, headers.length);
                for (int i = 0; i < nbCells; i++) {
                    jsonGenerator.writeFieldName(headers[i]);
                    jsonGenerator.writeString(values[i]);
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }



        return gson.fromJson(stringWriter.toString(), CurrencyRates.class);
    }
}