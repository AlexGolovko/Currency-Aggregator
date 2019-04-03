package com.golovkobalak.dto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.golovkobalak.model.Bank;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.golovkobalak.model.CurrencyRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;

@Service
public class CurrencyRateDTO {




    private static final Logger logger = LogManager.getLogger(CurrencyRateDTO.class);
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    private static final Gson gson = gsonBuilder.create();


    public static List<CurrencyRate> convertUploadFileToObj(MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("File is Empty");
        }
        final String bankName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        final String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);

        List<CurrencyRate> rates = null;
        try (InputStream stream = file.getInputStream()) {
            switch (fileType) {

                case "json":
                    rates = ParserJsonToList.getListFrom(stream);
                    break;
                case "xml":
                    rates = ParserXmlToList.getListFrom(stream);
                    break;
                case "csv":
                    rates = ParserCsvToList.getListFrom(stream);
                    break;
                default:
                    throw new FileUploadException();
            }
        } catch (IOException | FileUploadException e) {
            logger.error(e.getMessage());
        }
        Bank bank = new Bank(bankName);
        rates.forEach(rate -> rate.setBank(bank));
        return rates;

    }


    /*private static CurrencyRate convertXmlToObj(InputStream xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyRate.class);
            Unmarshaller unmarshaller = null;
            unmarshaller = jaxbContext.createUnmarshaller();
            return (CurrencyRate) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<CurrencyRate> convertCsvToObjs(InputStream cvsFileStream) {
		*//*CSVReader csvReader = new CSVReader(new InputStreamReader(cvsFile));
		Iterator<String[]> iterator = csvReader.iterator();
		String[] headers = iterator.next();
		JsonFactory jsonFactory = new JsonFactory();
		StringWriter stringWriter = new StringWriter();
		try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter)) {
			jsonGenerator.writeStartObject();
			// TODO Use reflection for add field name
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
		return gson.fromJson(stringWriter.toString(), CurrencyRate.class);*//*

        return (List<CurrencyRate>) new CsvToBeanBuilder(new InputStreamReader(cvsFileStream))
                .withType(CurrencyRate.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();
    }*/
}