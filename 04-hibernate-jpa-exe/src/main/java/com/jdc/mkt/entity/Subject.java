package com.jdc.mkt.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subject_tbl")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 45)
	private String name;
	
//	@ElementCollection
//	@CollectionTable(name = "map_tbl",
//	joinColumns = {
//			@JoinColumn(name = "sub_id")
//	})
//	@MapKeyColumn(name = "key_value")
//	private Map<Integer, String> maps;
	
	@OneToMany(mappedBy = "subject")
	private List<Teacher> teachers;
}
