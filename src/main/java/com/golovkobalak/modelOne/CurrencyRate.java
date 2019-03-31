package com.golovkobalak.modelOne;

import lombok.Data;

@Data
public class CurrencyRate {
	private Bank bank;
	private String currencyName;
	private double buyPrice;
	private double sellPrice;
}
