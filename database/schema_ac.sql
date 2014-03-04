CREATE TABLE accountant (
  accountantid int NOT NULL AUTO_INCREMENT,
  email varchar(80) DEFAULT NULL,
  firstname varchar(50) DEFAULT NULL,
  lastname varchar(50) DEFAULT NULL,
  mobile varchar(20) DEFAULT NULL,
  password varchar(80) DEFAULT NULL,
  city varchar(80) DEFAULT NULL,
  joindate date DEFAULT NULL,
  age int DEFAULT NULL,
  isprofilecreated int DEFAULT NULL,
  profilecreatedate date DEFAULT NULL,
  isHidden int DEFAULT NULL,
  
  PRIMARY KEY (accountantid)
) AUTO_INCREMENT=1120 

CREATE TABLE tracking (
  trackid int NOT NULL AUTO_INCREMENT,
  accountantid int DEFAULT NULL,
  businessid int DEFAULT NULL,
  email varchar(80) DEFAULT NULL,
  mobile varchar(20) DEFAULT NULL,
  ipaddress varchar(80) DEFAULT NULL,
  trackdate date DEFAULT NULL,
  feature varchar(100) DEFAULT NULL,
  
  PRIMARY KEY (trackid)
)