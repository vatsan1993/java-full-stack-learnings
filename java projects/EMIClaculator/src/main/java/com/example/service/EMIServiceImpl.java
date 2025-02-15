package com.example.service;

import com.example.model.EMI;

public class EMIServiceImpl implements EMIService {
	
	@Override
	public Double calculateEmi(EMI emi) {
		Double emiAmount;
		Double monthlyInterestRate = emi.getAnnualInterestRate()/(12 * 100);
		emiAmount = (emi.getPrincipalAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, emi.getNumEmi()))/( Math.pow(1 + monthlyInterestRate, emi.getNumEmi()) - 1);
		return emiAmount;
	}

}
