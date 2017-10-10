package com.axon.bank.form;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileForm {
	CommonsMultipartFile file;
	String name;
	int id;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		System.out.println("Setting File");
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("Setting Name");
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
