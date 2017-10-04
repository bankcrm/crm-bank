package com.axon.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;
import com.axon.bank.form.LoginForm;
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
	
	/**
	 * Rest Api upload
	 * @param applicantForm
	 * @return
	 */
	@RequestMapping(value="/application", method=RequestMethod.POST,
					consumes={"application/json", "application/xml"},
					produces={"application/json", "application/xml"})
	@ResponseBody
	public AppMessageResponse uploadApplicantRest(@RequestBody ApplicantForm applicantForm){
		System.out.println(applicantForm);
		bankService.add(applicantForm);
		AppMessageResponse appMessageResponse = new AppMessageResponse();
		appMessageResponse.setStatus("success");
		appMessageResponse.setMessage("applicant is upload");
		appMessageResponse.setDescription("applicant is uploaded using rest api");
		return appMessageResponse;
	}
	

	@RequestMapping(value="viewApplicants", method=RequestMethod.GET)
	public String printApplicants(Model model){
		List<ApplicantForm> applicants = bankService.selectPendingApplicants();
		for(ApplicantForm applicant : applicants){
			System.out.println(applicant.toString());
			model.addAttribute(applicant.toString());
		}
		model.addAttribute("applicants", applicants);
		return "applicantList";
	}
	
	@RequestMapping(value="agentcustomers", method=RequestMethod.GET)
	public String printCustomers(Model model){
		System.out.println("Gets an Agent's Customers");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
		List<CustomerForm> customers = bankService.selectAgentsCustomers(auth.getName());
		for(CustomerForm customer : customers){
			System.out.println(customer.toString());
			model.addAttribute(customer.toString());
		}
		model.addAttribute("customers", customers);
		return "agentHome";
	}
	
	@RequestMapping(value="/applications", method=RequestMethod.GET,
			produces={"application/json", "application/xml"})
	@ResponseBody
	public List<ApplicantForm> viewApplicants(){
		return bankService.selectAllApplicants();
	}
	
	@RequestMapping(value="/statuschange", method=RequestMethod.PUT,
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"})
	@ResponseBody
	public AppMessageResponse changeStatus(@RequestBody ApplicantForm applicant){
	System.out.println(applicant.getId());
	bankService.changeStatus(applicant.getId(),applicant.getStatus());
	AppMessageResponse appMessageResponse = new AppMessageResponse();
	appMessageResponse.setStatus("success");
	appMessageResponse.setMessage("status changed");
	return appMessageResponse;
	}
	

	@RequestMapping(value="/updatecustomer", method=RequestMethod.POST,
			consumes={"application/json", "application/xml"},
			produces={"application/json", "application/xml"})
	@ResponseBody
	public AppMessageResponse changeStatus(@RequestBody CustomerForm customer){
	System.out.println("Updating: " + customer);
	bankService.updateCustomer(customer);
	AppMessageResponse appMessageResponse = new AppMessageResponse();
	appMessageResponse.setStatus("success");
	appMessageResponse.setMessage("Customer Updated");
	return appMessageResponse;
	}


	@RequestMapping(value="leaderHome", method=RequestMethod.GET, produces= {"application/json"})
	@ResponseBody
	public List<LoginForm> getConnectedAgent(){
		List<LoginForm> agentList = bankService.getConnectedAgent();
		return agentList;
	}
	
	@RequestMapping(value="acceptedRequest", method=RequestMethod.GET, produces= {"application/json"})
	@ResponseBody
	public List<CustomerForm> getAcceptedApplicants(){
		List<CustomerForm> applicantList = bankService.getAcceptedApplicants();
		return applicantList;
	}
	

}
