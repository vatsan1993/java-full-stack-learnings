package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.EMI;
import com.example.service.EMIService;
import com.example.service.EMIServiceImpl;

/**
 * Servlet implementation class EMIServlet
 */
@WebServlet("/calculate-emi")
public class EMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private EMIService emiCalculator;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EMIServlet() {
        super();
        // TODO Auto-generated constructor stub
        emiCalculator = new EMIServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double principal = Double.parseDouble(request.getParameter("principal"));
		Double interest = Double.parseDouble(request.getParameter("interest"));
		Integer numEmis = Integer.parseInt(request.getParameter("numEmis"));
		EMI emi = new EMI(principal, interest, numEmis);
		List<String> emis =(List<String>) request.getSession().getAttribute("emis");
		if(emis == null) {
			emis = new ArrayList<String>();
		}
		Double emiAmount = emiCalculator.calculateEmi(emi);
		String emiResult = emi.toString() +", emiAmount= " +  Math.round(emiAmount * 100.0) / 100.0;
		emis.add(0, emiResult);
		request.getSession().setAttribute("emis", emis);
//		request.getRequestDispatcher("emi-calculator.jsp").forward(request, response);
		response.sendRedirect("emi-calculator.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
