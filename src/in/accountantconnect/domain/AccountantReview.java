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
@Table(name="accountantReview", uniqueConstraints=@UniqueConstraint(columnNames={"profileid"}))
@XmlRootElement(name = "accountantReview")

public class AccountantReview {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer reviewid;

	@Column
	private Integer profileid;

	@Column
	private String reviewdesc;

	public Integer getReviewid() {
		return reviewid;
	}

	public void setReviewid(Integer reviewid) {
		this.reviewid = reviewid;
	}

	public Integer getProfileid() {
		return profileid;
	}

	public void setProfileid(Integer profileid) {
		this.profileid = profileid;
	}

	public String getReviewdesc() {
		return reviewdesc;
	}

	public void setReviewdesc(String reviewdesc) {
		this.reviewdesc = reviewdesc;
	}
	
}












