package com.golovkobalak.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class CurrencyRate {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="bank")
    private Bank bank;
    @CsvBindByName(column = "name")
    @SerializedName("name")
    @JacksonXmlProperty(localName = "name")
    private String currencyName;
    @CsvBindByName(column = "buy")
    @SerializedName("buy")
    @JacksonXmlProperty(localName = "buy")
    private double buyPrice;
    @CsvBindByName(column = "sell")
    @SerializedName("sell")
    @JacksonXmlProperty(localName = "sell")
    private double sellPrice;
}

/*@Data
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
	}*/


