package com.axon.bank.form;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LoanApplicants")
public class ApplicantForm {

	String name;
	int age;
	String address;
	String mobile;
	int amount;
	String ssn;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	@Override
	public String toString() {
		return "ApplicantEntity [name=" + name + ", age=" + age + ", address=" + address + ", mobile=" + mobile
				+ ", amount=" + amount + ", ssn=" + ssn + "]";
	}
	
}