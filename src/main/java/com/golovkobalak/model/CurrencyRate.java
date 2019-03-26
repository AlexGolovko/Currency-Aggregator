package com.golovkobalak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "CurrencyRates")
@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue
    @XmlTransient
    private long id;

    private String bankName;
    @SerializedName(value = "currencyRate")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CurrencyRate_id")
    private List<CurrencyPrice> currencyData;

    @XmlElement(name = "CurrencyRate")
    public List<CurrencyPrice> getCurrencyData() {
        return currencyData;
    }
}
