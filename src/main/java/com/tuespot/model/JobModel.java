package com.tuespot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobModel {

	Long id;
	String title;
	String description;
	String location;
	String department;
	String experience;
	String jobType;
	String skills;
	boolean status;
	String type;
	Long companyId;
	

	

}
