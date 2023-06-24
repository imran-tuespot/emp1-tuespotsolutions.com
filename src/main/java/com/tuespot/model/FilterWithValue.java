package com.tuespot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterWithValue {

	private Long filterId;
	private Long filterValueId;

	
}
