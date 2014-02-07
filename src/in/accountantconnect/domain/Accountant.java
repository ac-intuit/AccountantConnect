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
@Table(name="accountant", uniqueConstraints=@UniqueConstraint(columnNames={"accountantid"}))
@XmlRootElement(name = "accountant")
public class Accountant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer accountantid;
	
	@Column
	private String firstName;
	
	@Column 
	private String lastName;
	
	@Column
	private String password;
	
	@Column
	private String city;
		
	@Column
	private Date joindate;
	
	@Column
	private Integer isProfileCreated;
	
	@Column
	private Date profileCreateDate;
	
	@Column
	private String email;
	
	@Column
	private String mobile;
	
	@Column
	private Integer age;
	
	@Column
	private Integer isHidden;

	public Integer getAccountantid() {
		return accountantid;
	}

	public void setAccountantid(Integer accountantid) {
		this.accountantid = accountantid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Integer getIsProfileCreated() {
		return isProfileCreated;
	}

	public void setIsProfileCreated(Integer profileCreated) {
		this.isProfileCreated = profileCreated;
	}

	public Date getProfileCreateDate() {
		return profileCreateDate;
	}

	public void setProfileCreateDate(Date profileCreationDate) {
		this.profileCreateDate = profileCreationDate;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}	
	
}
