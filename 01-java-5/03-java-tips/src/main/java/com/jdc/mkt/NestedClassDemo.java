package com.jdc.mkt;

public class NestedClassDemo {
	public static void main(String[] args) {
		
		
		A a = new A() {
			@Override
			void test() {
				System.out.println("Anonymous A");
			}
		};
		
		a.test();
	}
	
	void method() {
		C c = new C();
		c.test();
		
		
		
		// Local variable must be final or effectively final
		String name = "Local Class";
		
		// Local class
		class B extends A {
			@Override
			void test() {
				System.out.println(name);
			}
		}

		B b = new B();
		b.test();
	}
	
	
	private	class C extends A{
		@Override
		void test() {
			System.out.println("Local B");
		}
	}

	// Nested class or static nested class
	static class A {
		void test() {
			System.out.println("A");
		}
	}
}
