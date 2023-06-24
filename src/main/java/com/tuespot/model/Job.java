package com.tuespot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

	private Long id;
	private Integer cityId;
	private Long companyId;
	private String department;
	private String description;
	private String experience;
	private String jobType;
	private String location;
	private String skills;
	private boolean status;
	private String title;
	private String type;
	private Long workModeId;
	
	private List<FilterWithValue> filterWithValues;

	
}
