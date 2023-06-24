package com.tuespot.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Candidate {

	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private String mobileNumber;
	private String address;
	private Integer city;
	private Integer district;
	private Integer state;
	private Long pinCode;
	private String panCard;
	private String resume;
	private Date dateOfBirth;
	private boolean status;
	private Date createdOn;
	private Date modifiedOn;
	private String description;
	private String image;
	private String profileHeadline;
}
