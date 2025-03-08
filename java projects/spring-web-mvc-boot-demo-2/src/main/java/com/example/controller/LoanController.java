package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Loan;
import com.example.service.LoanService;

@Controller
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loan")
	public String showLoanForm() {
		return "loan-form-page";
	}
	
	@PostMapping("/loan")
	public ModelAndView receiveLoanFromForm(@ModelAttribute Loan loan) {
		loanService.computeSI(loan);
		return new ModelAndView("loan-output-page", "loan", loan);
	}
}
