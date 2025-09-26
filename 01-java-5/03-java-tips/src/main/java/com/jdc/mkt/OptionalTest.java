package com.jdc.mkt;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		
		var val  = useOptional("hello");
		System.out.println(val.orElse("no value"));
	}
	
	static Optional<String> useOptional(String value) {
		return Optional.ofNullable(value);
	}
}
