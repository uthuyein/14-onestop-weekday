package com.jdc.mkt.defaultAnno;

import java.util.List;

public class DefaultAnnoDemo {

	
}

@SuppressWarnings("serial")
class X implements java.io.Serializable {
	
	@SuppressWarnings("unused")
	void show() {
		String name = "X";
		System.out.println("X.show");
	}
}
@SuppressWarnings("serial")
class Y extends X{}

class A{
	
	X show() {
		System.out.println("A.show");
		return new X();
	}
	
	void doSomething(@SuppressWarnings("unchecked") List<String>... args) {
		System.out.println("A.doSomething");
	}
}

class B extends A {
	
	@Override
	public Y show() {
		System.out.println("B.show");
		return new Y();
	}
}

