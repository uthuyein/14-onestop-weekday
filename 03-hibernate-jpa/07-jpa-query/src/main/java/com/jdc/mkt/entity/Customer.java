package com.jdc.mkt.entity;

import org.hibernate.annotations.ColumnDefault;

import com.jdc.mkt.entity.dto.SelectMemberAndCount;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_tbl")
@NamedNativeQuery(
		name = "selectMemberAndCount",
		query = "select member_type,count(*) qty from customer_tbl group by member_type",
		resultSetMapping = "selectMemberCount"
		)
@SqlResultSetMapping(
		name = "selectMemberCount",
		classes = {
		@ConstructorResult(
				targetClass = SelectMemberAndCount.class,
				columns = {
						@ColumnResult(name = "member_type"),
						@ColumnResult(name = "qty")
				}
				)
			})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true,length = 45)
	private String name;
	
	@ColumnDefault("true")
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_type")
	private MemberType memberType;
	
	@OneToOne(mappedBy = "customer")
	private Contact contact;
	
	public enum MemberType{
		NoMember,Silver,Gold,Platinum,Diamond
	}
}
