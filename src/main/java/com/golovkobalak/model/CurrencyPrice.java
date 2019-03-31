package com.golovkobalak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@XmlRootElement(name = "CurrencyRate")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Entity
class CurrencyPrice {
    @Expose
    @Id
    @GeneratedValue
    @XmlTransient
    private long id;

    private String name;
    @XmlElement(name = "buy")
    @SerializedName("buy")
    private Double buyPrice;
    @XmlElement(name = "sell")
    @SerializedName("sell")
    private Double sellPrice;


}
