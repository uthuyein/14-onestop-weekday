package com.jdc.mkt.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollment_tbl")
public class Enrollment {

	@EmbeddedId
	private EnrollmentPk id;
	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private Student student;
	@ManyToOne
	private Classroom classroom;
	
}
