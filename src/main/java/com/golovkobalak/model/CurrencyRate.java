package com.golovkobalak.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

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
