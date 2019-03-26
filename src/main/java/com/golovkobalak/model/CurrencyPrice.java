package com.golovkobalak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

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
