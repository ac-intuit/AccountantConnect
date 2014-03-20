package in.accountantconnect.domain;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="accountantProfile", uniqueConstraints=@UniqueConstraint(columnNames={"profileid"}))
@XmlRootElement(name = "accountantProfile")
public class AccountantProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer profileid;

	@Column
	private Integer accountantid;
	
	@Column
	private String imgLink;
	


	@Column
	private String email;

	@Column
	private String mobile;

	@Column
	private String firstName;

	@Column 
	private String lastName;

	@Column
	private String city;

	@Column
	private String companyName;

	@Column
	private String companyAddress;

	@Column
	private String speciality;

	@Column
	private String shortDesc;

	@Column
	private String experience;

	@Column
	private String clients;

	@Column
	private String facebookid;

	@Column
	private String linkedinid;

	@Column
	private String twitterid;

	@Column 
	private String education;

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}


	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getFacebookid() {
		return facebookid;
	}

	public void setFacebookid(String facebookid) {
		this.facebookid = facebookid;
	}

	public String getLinkedinid() {
		return linkedinid;
	}

	public void setLinkedinid(String linkedinid) {
		this.linkedinid = linkedinid;
	}

	public String getTwitterid() {
		return twitterid;
	}

	public void setTwitterid(String twitterid) {
		this.twitterid = twitterid;
	}

	public String getSpeciality() {
		return speciality;
	}

	public Integer getAccountantid() {
		return accountantid;
	}

	public void setAccountantid(Integer accountantid) {
		this.accountantid = accountantid;
	}


	public String getCity() {
		return city;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}



	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String accountantEmail) {
		this.email = accountantEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	

	public void setCompanyName(String companyName) {
		this.companyName=companyName;

	}

	public void setCompanyAddress(String companyAddress) {
		// TODO Auto-generated method stub
		this.companyAddress=companyAddress;

	}

	public void setSpeciality(String speciality) {
		// TODO Auto-generated method stub
         this.speciality=speciality;
	}



}

