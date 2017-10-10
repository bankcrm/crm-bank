package com.axon.bank.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customers")
public class CustomerEntity {

	@Id
	private int id;
	@Column(length=255)
	String name;
	@Column
	int age;
	@Column(length=255)
	String address;
	@Column(length=255)
	String mobile;
	@Column
	int amount;
	@Column(length=255)
	String ssn;
	@Column
	Timestamp dateCreated;
	@Column(length=255)
	String gender;
	@Column(length=255)
	String city;
	@Column(length=255)
	String company;
	@Column
	boolean salaried;
	@Column
	int salary;
	@Column(length=255)
	String salaryAccount;
	@Column
	Timestamp companyJoinDate; 
	@Column
	long amountOfExperience;
	@Column
	long timeInArea;
	@Column
	Timestamp lastmove;
	@Column(length=255)
	String homedetails;
	@Column
	Timestamp dob;
	@Column(length=255)
	String loanDetails;
	@Column
	int emiCount;
	@Column(length=255)
	String email;
	@Column
	boolean document;
	
	public boolean isDocument() {
		return document;
	}
	public void setDocument(boolean document) {
		this.document = document;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isSalaried() {
		return salaried;
	}
	public void setSalaried(boolean salaried) {
		this.salaried = salaried;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getSalaryAccount() {
		return salaryAccount;
	}
	public void setSalaryAccount(String salaryAccount) {
		this.salaryAccount = salaryAccount;
	}
	public Timestamp getCompanyJoinDate() {
		return companyJoinDate;
	}
	public void setCompanyJoinDate(Timestamp companyJoinDate) {
		this.companyJoinDate = companyJoinDate;
	}
	public long getAmountOfExperience() {
		return amountOfExperience;
	}
	public void setAmountOfExperience(long amountOfExperience) {
		this.amountOfExperience = amountOfExperience;
	}
	public long getTimeInArea() {
		return timeInArea;
	}
	public void setTimeInArea(long timeInArea) {
		this.timeInArea = timeInArea;
	}
	public Timestamp getLastmove() {
		return lastmove;
	}
	public void setLastmove(Timestamp lastmove) {
		this.lastmove = lastmove;
	}
	public String getHomedetails() {
		return homedetails;
	}
	public void setHomedetails(String homedetails) {
		this.homedetails = homedetails;
	}
	public Timestamp getDob() {
		return dob;
	}
	public void setDob(Timestamp dob) {
		this.dob = dob;
	}
	public String getLoanDetails() {
		return loanDetails;
	}
	public void setLoanDetails(String loanDetails) {
		this.loanDetails = loanDetails;
	}
	public int getEmiCount() {
		return emiCount;
	}
	public void setEmiCount(int emiCount) {
		this.emiCount = emiCount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
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
		return "CustomerEntity [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", mobile="
				+ mobile + ", amount=" + amount + ", ssn=" + ssn + ", dateCreated=" + dateCreated + ", gender=" + gender
				+ ", city=" + city + ", company=" + company + ", salaried=" + salaried + ", salary=" + salary
				+ ", salaryAccount=" + salaryAccount + ", companyJoinDate=" + companyJoinDate + ", amountOfExperience="
				+ amountOfExperience + ", timeInArea=" + timeInArea + ", lastmove=" + lastmove + ", homedetails="
				+ homedetails + ", dob=" + dob + ", loanDetails=" + loanDetails + ", emiCount=" + emiCount + ", email="
				+ email + ", document=" + document + "]";
	}
	
}
