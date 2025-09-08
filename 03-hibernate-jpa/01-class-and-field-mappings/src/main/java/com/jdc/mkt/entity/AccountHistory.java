package com.jdc.mkt.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@IdClass(AccountHistoryPk.class)
public class AccountHistory {

//	@EmbeddedId
//	private AccountHistoryPk id;
	
	@Id
	private int id;
	@Id
	private LocalDateTime startTime;
	@Id
	private LocalDateTime endTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "date default(current_date)")
	private Date date;
	
	@Transient
	private transient boolean active;
	
	@Column(length = 255,
			nullable = false,
			columnDefinition = "text check(char_length(description)>= 100)")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('CEARTE','UPDATE','SELECT') default 'SELECT' ")
	private Action action;
	
	@Lob
	private String image;
	
	private enum Action{
		CEARTE,UPDATE,SELECT;
	}
}
