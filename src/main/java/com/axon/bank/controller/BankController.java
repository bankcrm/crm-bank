package com.axon.bank.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.axon.bank.dao.entity.CustomerEntity;
import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;
import com.axon.bank.form.FileForm;
import com.axon.bank.form.LoginForm;
import com.axon.bank.service.BankService;
import com.axon.util.UploadItem;

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
	@RequestMapping(value="applyloan", method=RequestMethod.POST)
	public String uploadApplicantWithOutRest(@ModelAttribute ApplicantForm applicantForm, Model model){
		System.out.println(applicantForm);
		bankService.add(applicantForm);
		return "login";
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
		bankService.assignCustoemerToAgent();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
		List<CustomerForm> customers = bankService.selectAgentsCustomers(auth.getName());
		
		for(CustomerForm customer : customers){
			System.out.println(customer.toString());
			model.addAttribute(customer.toString());
		}
		model.addAttribute("username",auth.getName());
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
	public AppMessageResponse updateCustomer(@RequestBody CustomerForm customer){
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	System.out.println("Updating: " + customer);
	bankService.updateCustomer(customer);
	AppMessageResponse appMessageResponse = new AppMessageResponse();
	appMessageResponse.setStatus("success");
	appMessageResponse.setMessage("Customer Updated");
	return appMessageResponse;
	}


	@RequestMapping(value="/leaderHome", method=RequestMethod.GET, produces= {"application/json"})
	@ResponseBody
	public List<LoginForm> getConnectedAgent(){
		List<LoginForm> agentList = bankService.getConnectedAgent();
		return agentList;
	}
	
	@RequestMapping(value="/acceptedRequest", method=RequestMethod.GET, produces= {"application/json"})
	@ResponseBody
	public List<CustomerForm> getAcceptedApplicants(){
		List<CustomerForm> applicantList = bankService.getAcceptedApplicants();
		return applicantList;
	}
	
	@RequestMapping(value="/progressStatus", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Object> getProgressList(){
		System.out.println("$$$$$$%%%%%%%%@@@@@@_____Getting progressList in controller");
		return bankService.getProgessStatus();
	}
	
	@RequestMapping(value="/assignedRequest/{id}/{username}", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public AppMessageResponse assginCustomerRequest(@PathVariable("id") int id, @PathVariable("username") String username){
		bankService.assignCustoemerToAgent(id, username);
		AppMessageResponse appMessageResponse = new AppMessageResponse();
		appMessageResponse.setStatus("success");
		appMessageResponse.setMessage("Request Assigned");
		return appMessageResponse;
	}
	
	@RequestMapping(value="/autoAssignRequest", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public AppMessageResponse autoAssignCustomerRequest(){
		bankService.assignCustoemerToAgent();
		AppMessageResponse appMessageResponse = new AppMessageResponse();
		appMessageResponse.setStatus("success");
		appMessageResponse.setMessage("Request Assigned");
		return appMessageResponse;
	}
	
	@RequestMapping(value="/uploaddocument/{id}/{name}", method=RequestMethod.GET)
	public String createLink(@PathVariable("id") int id, @PathVariable("name") String name, Model model){
	model.addAttribute("name",name);
	model.addAttribute("id",id);
	model.addAttribute(new UploadItem());
	return "fileUpload";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
             // to actually be able to convert Multipart instance to byte[]
             // we have to register a custom editor
             binder.registerCustomEditor(byte[].class,
                                new ByteArrayMultipartFileEditor());
             // now Spring knows how to handle multipart object and convert them
    }
	
	@RequestMapping(value="/finalize/{id}", method = RequestMethod.GET)
	public String finalize(@PathVariable("id") int id, Model model){
		if(bankService.isCompleted(id)>=100){
			bankService.setStatus(id);
			bankService.sendCompletedEmail(id);
			return "finalizeSuccess";
		} else {
			return "finalizeFailure";
			
		}

	}
	
	@RequestMapping(value="/uploaddocument/{id}/{name}/upload", method = RequestMethod.POST)
	public String create(UploadItem uploadItem, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @PathVariable("id") int id, @PathVariable("name") String name) {
		System.out.println(uploadItem);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "redirect:/bank/uploaddocument/" + id + "/" + name;
		}

		// Some type of file processing...
		System.err.println("-------------------------------------------");
		try {
			MultipartFile file = uploadItem.getFileData();
			String fileName = null;
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			
			/*File f = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			f = f.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
			String path = f.toString() + "/crm-bank";*/
			String path = System.getProperty("user.home");


			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > 100000) {
					System.out.println("File Size:::" + file.getSize());
					return "redirect:/bank/uploaddocument/" + id + "/" + name;
				}
				System.out.println("size::" + file.getSize());
				File filepath = new File(path + "/BankFileSystem/" + id + "/" + name);
				System.out.println(filepath.getAbsolutePath());
				if(!filepath.exists()){
					filepath.mkdirs();
				}
				fileName = path + "/BankFileSystem/" + id + "/" + name + "/"  
						+ file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[100000];
				while ((readBytes = inputStream.read(buffer, 0, 100000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			} else {
				return "redirect:/bank/uploaddocument/" + id + "/" + name;
			}
			bankService.setDocument(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/bank/agentcustomers";
	}
	
	@RequestMapping(value="/document", method = RequestMethod.POST)
	public void viewDocument(@RequestBody String file,HttpServletRequest req,HttpServletResponse resp){
		System.out.println(file);
		file=file.substring(5).replaceAll("%3A", ":").replaceAll("%5C", "/").replaceAll("\\+", " ");
		System.out.println(file); 
		try {
			InputStream inputStream = null;
			
				inputStream = new FileInputStream(new File(file));

				int readBytes = 0;
				byte[] buffer = new byte[100000];
				while ((readBytes = inputStream.read(buffer, 0, 100000)) != -1) {
					
				}
				String mimeType= URLConnection.guessContentTypeFromName(new File(file).getName());
				System.out.println(mimeType);
				 resp.setContentType(mimeType);
				 ServletOutputStream outputStream=resp.getOutputStream();
				 if(buffer!=null) {
					 outputStream.write(buffer);
					 outputStream.flush();
				 }

				outputStream.close();
				inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/approvedcustomers", method = RequestMethod.GET)
	public String viewFinishedCustomers(Model model){
		List<CustomerForm> customers = bankService.getCompletedCustomers();
		HashMap<Integer,File[]> files = new HashMap<>();
		
		/*File f = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println(f.toString());
		f = f.getParentFile();
		System.out.println(f);*/
		
		// Some type of file processing...
		System.err.println("-------------------------------------------");
		for(CustomerForm customer: customers){
			try {
				String fileName = System.getProperty("user.home") + "/BankFileSystem/" + customer.getId() + "/" + customer.getName();
			
					File filepath = new File(fileName);
					File[] idFiles = filepath.listFiles();
					files.put(customer.getId(), idFiles);
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		model.addAttribute("customers", customers);
		model.addAttribute("files",files);
		System.out.println(files);
		return "completedcustomers";
	}

	
}
