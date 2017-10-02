package com.axon.bank.controller;

import java.util.List;

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
@RequestMapping("/bank")
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
		model.addAttribute("message", "Hello my banker");
		return "uploadApplicant";
	}
	
	
	@RequestMapping(value="viewApplicants", method=RequestMethod.GET)
	public String uploadApplicant(Model model){
		List<ApplicantForm> applicants = bankService.selectAllApplicants();
		for(ApplicantForm applicant : applicants){
			System.out.println(applicant.toString());
			model.addAttribute(applicant.toString());
		}
		return "applicantList";
	}
}
