package com.jdc.mkt.converter;

import java.time.LocalDate;

import com.jdc.mkt.entity.Card;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CardConverter implements AttributeConverter<Card, String>{

	@Override
	public String convertToDatabaseColumn(Card card) {
		return card.toString();
	}

	@Override
	public Card convertToEntityAttribute(String dbData) {
		var array = dbData.split(":");
		return new Card(array[0], 
				LocalDate.parse(array[1]),
				LocalDate.parse(array[2]));
	}

}
