package com.jdc.mkt.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("S")
public class Student extends Account {
	
	@Enumerated(EnumType.STRING)
	//@Column(columnDefinition = "enum('NoScholar','HalfScholar','FullScholar') default 'NoScholar'")
	private ScholarshipType scolarshipType = ScholarshipType.NoScholar;
	
	public enum ScholarshipType{
		NoScholar,HalfScholar,FullScholar
	}
}
