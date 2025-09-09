package com.jdc.mkt;

import java.util.Map;
import java.util.TreeMap;

public class CollectionDemo {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
//		Set<String> set = new HashSet<String>();
//					set = new LinkedHashSet<String>();
//					set = new TreeSet<String>();
//					
//		List<String> list = new ArrayList<String>();
//					 list = new LinkedList<String>();
					 
	   Map<Human, String> map = new TreeMap<>();
	   						
	   			map.put(new Human("Patrick",22),"Patrick");	
	   			map.put(new Human("Kelvin",34), "Kelvin");
	   			map.put(new Human("James",42), "James");
	   			map.put(new Human("Bob",41), "Bob");
	   			
	   			for(Map.Entry e : map.entrySet()) {
	   				System.out.println(e.getKey()+"\t"+ e.getValue());
	   			}
	}
	
	public enum Days{
		Mon,Tue
	}
}

@SuppressWarnings("unused")
class Human implements Comparable<Human>{

	private String name;
	private int age;
	
	public Human(String name,int age) {
		this.name = name;
		this.age = age;
	}
	

	@Override
	public int compareTo(Human h) {
		return name.compareTo(h.name);
//		if(age < h.age) {
//			return -1;
//		}
//		return 1;
	}
}

