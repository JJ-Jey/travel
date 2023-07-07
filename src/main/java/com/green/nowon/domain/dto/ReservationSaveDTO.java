package com.green.nowon.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ReservationSaveDTO {
	
	private String arrival;
	private String arrivalDate;
	private String departure;
	private String departureDate;
	private String grade;
	private int passenger;
	private long mno;
	
}
