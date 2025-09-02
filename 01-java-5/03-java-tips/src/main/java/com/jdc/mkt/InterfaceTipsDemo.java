package com.jdc.mkt;

public class InterfaceTipsDemo {

}

//1. Default Method with deadly death problem
class C implements A, B {

	@Override
	public void test() {
		A.super.test();
	}

}

interface A {

	//effective final static variable in interface
	String NAME = "Interface A";
	
	default void test() {
		//Can't reassign NAME Value to interface variable
		System.out.println(NAME);
	}
		
	//3.private method in interface
	static void staticTest() {
		System.out.println("Static Method in Interface");	
	}
	
	//2.static method in interface
	@SuppressWarnings("unused")
	private void privateTest() {
		System.out.println("Privete Method in interface");
	}
}

interface B {

	default void test() {
		System.out.println("B");
	}
}	


