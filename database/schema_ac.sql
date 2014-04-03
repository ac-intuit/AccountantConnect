--------------------------------
--       Accountant & profile
--------------------------------
CREATE TABLE accountant (
  accountantid int NOT NULL AUTO_INCREMENT,
  email varchar(80),
  firstname varchar(50),
  lastname varchar(50) ,
  password varchar(80) ,
  joindate date ,
  isprofilecreated int ,
  profilecreatedate date ,
  isHidden int ,
  
  --Profile information
  mobile varchar(20) ,
  businessphone varchar(20)  ,
  city varchar(80) ,
  age int ,
  area varchar(100) ,
  
  photofilename varchar(100) ,
  shortdescription text,
  longdescription text,
  
  linkedinprofile text,
  fbprofile text,
  gplusprofile text,
    
  yearofexp int,
  areasofexpertise text,
  education text,
  certifications text,
  
  addressline1 varchar(200),
  addressline2 varchar(200),
  state varchar(100),
  pincode int,
  country varchar(100),
  
  PRIMARY KEY (accountantid)
) AUTO_INCREMENT=1120 


-------------------------------
--   Tracking table
--   Should be merged with GA
-------------------------------
CREATE TABLE tracking (
  trackid int NOT NULL AUTO_INCREMENT,
  accountantid int ,
  businessid int ,
  email varchar(80) ,
  mobile varchar(20) ,
  ipaddress varchar(80) ,
  trackdate date ,
  feature varchar(100),
  
  PRIMARY KEY (trackid)
)

