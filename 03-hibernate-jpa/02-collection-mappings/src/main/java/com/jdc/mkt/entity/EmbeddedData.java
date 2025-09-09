package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmbeddedData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String data;
	private int data2;
	
	@ElementCollection
	List<String> list3;
}
