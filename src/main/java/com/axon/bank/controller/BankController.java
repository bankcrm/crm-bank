package com.axon.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.axon.bank.form.ApplicantForm;
import com.axon.bank.service.BankService;

@Controller
//@RequestMapping("/bank")
public class BankController {

	@Autowired
	@Qualifier("BankServiceImpl")
	private BankService bankService;

	private BankController(){
		System.out.println("Container is loading");
	}
	@RequestMapping(value="uploadApplicant", method=RequestMethod.POST)
	public String uploadApplicant(@ModelAttribute ApplicantForm applicantForm, Model model){
		bankService.add(applicantForm);
		model.addAttribute("Hello my banker");
		return "uploadApplicant";
	}
}
