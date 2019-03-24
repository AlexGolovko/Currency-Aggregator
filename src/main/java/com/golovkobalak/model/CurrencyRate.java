package com.golovkobalak.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CurrencyRate")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
class CurrencyRate {
    private String name;
    @XmlElement(name = "buy")
    @SerializedName("buy")
    private Double buyPrice;
    @XmlElement(name = "sell")
    @SerializedName("sell")
    private Double sellPrice;


}
