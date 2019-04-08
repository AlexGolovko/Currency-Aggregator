package com.golovkobalak.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@Entity
@Component
@Scope("prototype")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank")
    private Bank bank;
    @CsvBindByName(column = "name")
    @SerializedName("name")
    @JacksonXmlProperty(localName = "name")
    private String name;
    @CsvBindByName(column = "buy")
    @SerializedName("buy")
    @JacksonXmlProperty(localName = "buy")
    private double buyPrice;
    @CsvBindByName(column = "allowBuy")
    @SerializedName("allowBuy")
    @JacksonXmlProperty(localName = "allowBuy")
    private boolean allowBuy;
    @CsvBindByName(column = "sell")
    @SerializedName("sell")
    @JacksonXmlProperty(localName = "sell")
    private double sellPrice;
    @CsvBindByName(column = "allowSell")
    @SerializedName("allowSell")
    @JacksonXmlProperty(localName = "allowSell")
    private boolean allowSell;
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


