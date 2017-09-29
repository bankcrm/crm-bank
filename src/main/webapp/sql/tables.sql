Create Table LoanApplicants(Name Varchar(255), Age int, Address Varchar(255), Mobile VarChar(255), Amount int, SSN VarChar(11));



ALTER TABLE LoanApplicants ADD id int;
ALTER TABLE LoanApplicants ADD PRIMARY KEY (id);
alter table LoanApplicants modify column id int auto_increment;