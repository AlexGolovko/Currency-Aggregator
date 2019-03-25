package com.golovkobalak.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "CurrencyRates")
@Entity
public class CurrencyRates {
    @Id
    @GeneratedValue
    private long id;

    private String bankName;
    private Date initDate;
    @SerializedName(value = "currencyRate")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CurrencyRate_id")
    private List<CurrencyRate> currencyData;

    @XmlElement(name = "CurrencyRate")

    public List<CurrencyRate> getCurrencyData() {
        return currencyData;
    }
}
