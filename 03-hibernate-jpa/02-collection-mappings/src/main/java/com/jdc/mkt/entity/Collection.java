package com.jdc.mkt.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "collection_tbl")
public class Collection {

	@Id
	private int id;
	
	@ElementCollection
	@CollectionTable(name = "set_tbl",
		joinColumns = {
				@JoinColumn(name = "col_id")
		})
	
	@Column(name = "setValue")
	private Set<EmbeddedData> sets;
	@ElementCollection
	@CollectionTable(name = "list_tbl",
		joinColumns = {
				@JoinColumn(name = "col_id",referencedColumnName = "id")
		})
	
	private List<String> lists;
	@ElementCollection
	@CollectionTable(name = "map_tbl",
		joinColumns = {
				@JoinColumn(name = "col_id",referencedColumnName = "id")
		})
	@MapKeyColumn(name = "keyValue")
	@MapKeyEnumerated(EnumType.STRING)
	private Map<MapType, String> maps;
	
	
	enum MapType{
		T1,T2
	}
}
