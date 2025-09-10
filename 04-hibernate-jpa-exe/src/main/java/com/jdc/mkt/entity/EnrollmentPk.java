package com.jdc.mkt.entity;

import java.io.Serializable;
import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Embeddable
public class EnrollmentPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "open_year",columnDefinition = "year not null")
	private Year year;
	@Column(name = "student_id")
	private int studentId;
}
