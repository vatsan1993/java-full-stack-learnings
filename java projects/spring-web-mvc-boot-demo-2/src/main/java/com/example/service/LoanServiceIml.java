package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.Loan;

@Service
public class LoanServiceIml implements LoanService{

	@Override
	public void computeSI(Loan loan) {
		// TODO Auto-generated method stub
		loan.setSimpleInterest(loan.getPrincipal() * loan.getInterestRate() * loan.getTime()/100);
		loan.setPayableAmount(loan.getPrincipal() + loan.getSimpleInterest());
		
	}

}
