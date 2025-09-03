package com.jdc.mkt;

public class HashAndEqualDemo {

	public static void main(String[] args) {
		Hello h1 = new Hello("Hello message",1);
		Hello h2 = new Hello("Hello message",1);
		
		System.out.println("Condition : "+h1.equals(h2));
		System.out.println(h1);
		
		String s1 = new String("Hello");
		String s2 = "Hello";
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
	}
	
	private static class Hello {
		private String message;
		private int count;

		private Hello(String message,int count){
			this.message = message;
			this.count = count;
		}
		
		@Override
		public String toString() {
			return message+":"+count;
		}

//		@Override
//		public int hashCode() {
//			return Objects.hash(count, message);
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Hello other = (Hello) obj;
//			return count == other.count && Objects.equals(message, other.message);
//		}
		
		
	}

}
