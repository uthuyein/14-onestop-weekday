package com.jdc.mkt;

public class EnumDemo {

	public static void main(String[] args) {
		
		Day today = Day.MONDAY;
		today.name();
		today.ordinal();
		today.toString();
		Day.valueOf("MONDAY");
		
		System.out.println(today.ordinal());
		System.out.println(today.name());
	}
}

enum Day {
	MONDAY {
		@Override
		void showDay() {
			// TODO Auto-generated method stub
			
		}
	}, TUESDAY {
		@Override
		void showDay() {
			// TODO Auto-generated method stub
			
		}
	}, WEDNESDAY {
		@Override
		void showDay() {
			// TODO Auto-generated method stub
			
		}
	};
	abstract void showDay();
}







