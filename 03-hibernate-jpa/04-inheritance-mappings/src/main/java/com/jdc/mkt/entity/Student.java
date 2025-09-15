package com.jdc.mkt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Student extends Account {
	
	@Enumerated(EnumType.STRING)
	private ScholarshipType scolarshipType;
	
	public enum ScholarshipType{
		NoScholar,HalfScholar,FullScholar
	}
}
