package com.golovkobalak.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "CurrencyRates")

public class CurrencyRates {

    private String bankName;
    private Date initDate;
    @SerializedName(value = "currencyRate")
    private List<CurrencyRate> currencyData;

    @XmlElement(name = "CurrencyRate")
    public List<CurrencyRate> getCurrencyData() {
        return currencyData;
    }
}
