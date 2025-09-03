package com.jdc.mkt.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Person {
	
	@Id
	private int id;
	private String name;
	private int age;
	private Gender gender;
	private LocalDate date;
	
	public enum Gender{
		Male,Female,Other
	}
}
