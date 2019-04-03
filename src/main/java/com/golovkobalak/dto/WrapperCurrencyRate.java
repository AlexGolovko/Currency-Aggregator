package com.golovkobalak.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.golovkobalak.model.CurrencyRate;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Data
@JacksonXmlRootElement
public class WrapperCurrencyRate {

    @JacksonXmlProperty(localName = "CurrencyRate")
    @JacksonXmlElementWrapper(useWrapping = false)
   private List<CurrencyRate> root;

}
