package com.golovkobalak.modelOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) {
		List<CurrencyRate> list = new ArrayList();

		Bank bank = new Bank();
		bank.setBankName("bank");

		for (int i = 0; i < 5; i++) {
			list.add(new CurrencyRate());

		}

		list.forEach(rate -> {
			rate.setBank(bank);
			rate.setBuyPrice(new Random().nextDouble());
			rate.setSellPrice(new Random().nextDouble());
			rate.setCurrencyName("currName");
		});

		String result = new Gson().toJson(list);
		System.out.println(result);
		ArrayList<CurrencyRate> res=new Gson().fromJson(result, ArrayList.class);

		System.out.println(res);
	}

}