Create Table LoanApplicants(Name Varchar(255), Age int, Address Varchar(255), Mobile VarChar(255), Amount int, SSN VarChar(11));



ALTER TABLE LoanApplicants ADD id int;
ALTER TABLE LoanApplicants ADD PRIMARY KEY (id);
alter table LoanApplicants modify column id int auto_increment;


create table loginEntity(
	lid int not null auto_increment,
	username varchar(50),
	password varchar(50),
	role varchar(50),
	primary key (lid)
);

ALTER TABLE loginEntity ADD status int;

create table AgentCustomerRelation(
	id int not null auto_increment,
	agent int default 1,
	customerId int,
	status double,
	primary key (id),
	foreign key (customerId) references Customers (id),
	foreign key (agent) references loginEntity (lid)
);