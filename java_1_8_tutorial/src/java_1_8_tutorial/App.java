package java_1_8_tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import serializeObjTest.Employee;

public class App {
	
	public static void main(String[] args) {
		
		List<Integer> testInteger = new ArrayList<Integer>();
		
		testInteger.add(2);
		testInteger.add(5);
		testInteger.add(6);
		
		testInteger.stream().reduce(Math::max).get();
		
		System.out.println("===========");
		
		List<String> testList = new ArrayList<String>();
		
		testList.add("abc");
		testList.add("cde");
		testList.add("fgh");
		
		//forEach accepts lambda argument
		//List cannot filter, only can loop. required to create a new list to perform filter or map
//		testList.forEach(x -> System.out.println(x));
//		testList.forEach(System.out::println);
		
//		Stream<String> streamTest =testList.stream().filter(x -> {
//			return !(x.equals("abc"));
//		});
//		
//		testList = streamTest.collect(Collectors.toList());
//		
//		testList.forEach(System.out::println);
		//Stream use with optional
		
		//maybe flatmap can filter multiple list of object and find something like highest number within?
		Stream<List<String>> testMultipleStream = Stream.of(
				Arrays.asList("ABC","DEF"),Arrays.asList("GHI","JKL"),Arrays.asList("MNO","PQR")
		);
		
		testMultipleStream.flatMap(list->list.stream()).forEach(System.out::println);
		
		System.out.println("===========");
		
		//test case
		Employee testEmp1 = new Employee();
		testEmp1.setName("test1");
		Employee testEmp2 = new Employee();
		testEmp2.setName("test2");
		Employee testEmp3 = new Employee();
		testEmp3.setName("test3");
		
		List<Employee> testEmpList1 = new ArrayList<Employee>();testEmpList1.add(testEmp1);
		
		List<Employee> testEmpList2 = new ArrayList<Employee>();testEmpList2.add(testEmp2);
		
		List<Employee> testEmpList3 = new ArrayList<Employee>();testEmpList3.add(testEmp3);testEmpList3.add(null);
		
		List<String> nameList = new ArrayList<String>();
		
		//Optional prevent npe (null pointer exception) during run time
//		Optional.ofNullable(testEmpList3).ifPresent(o->{	//list is present
//			System.out.println(o);
//			o.forEach(x-> {	//during looping the list, there is one item is null
//				System.out.println(x.getName());
//			});
//		});
		
		//TESTINGS
//		testEmpList3.stream()
//			.map(o -> Optional.ofNullable(o))
//			.filter(Optional::isPresent)
		
		//3 steps: 
		testEmpList3.stream()
			.map(o -> Optional.ofNullable(o))			//1. return a list of optional which is not null
			.filter(Optional::isPresent)		//2. filter the return which is present
			.map(Optional::get)		//3. get back the value
			.forEach(o -> {
				System.out.println(o.getName());
			});
		
		nameList = testMe(testEmpList3.stream())
			.map(Employee::getName)			//.map return the new value based on the return elements
			.collect(Collectors.toList());
		
		nameList.forEach(System.out::println);	//Consumer allows lambda expression
//		nameList.forEach(((Consumer<String>)System.out::println).andThen(System.out::println));
		
		testEmpList3.forEach(o -> {
			if(o != null)
				System.out.println(o.getName());
		});
		
		Stream<List<Employee>> testMultipleEmpStream = Stream.of(testEmpList1,testEmpList2,testEmpList3);
		
//		Optional.ofNullable(testMultipleEmpStream).ifPresent(stream -> {
//			stream.flatMap(emp->emp.stream()).forEach(emp -> {
//				nameList.add(emp.getName());
//			});
//		});
		
		System.out.println("===========");
		
		//forEach accepts consumer function
		testList.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		//ForEach is a interface method (intermediate)
		//Interface can have method -> with default /static keyword
		//default keyword cannot be same for both interface that required to be implement together with a class. (avoid Diamond Problem)
		
		//@FunctionalInterface required only one abstract method,
		//If the interface is marked as functional, it is easier to apply lambda expression as it contains only one abstract method
		//LAMBDA dun have runtime benefits, it only make the code cleaner by reducing few lines
		
		Collections.sort(testList, (String o1, String o2) -> {
			return 1;
		});
		
		//Stream API
		List<Integer> testMe = IntStream.range(1, 99999).boxed().collect(Collectors.toList());
		
		//testMe.parallelStream().forEach( p -> System.out.println(p));		// Not in Order though it is list. [as said, is helpful working in huge collection]
		//testMe.stream().forEach( p -> System.out.println(p));				// In order 
	
		Integer testInt = testMe.parallelStream().filter(p-> p > 10).mapToInt(i -> i).sum();
		
		System.out.println(testInt);
		
		Integer testABC = testMe.stream().filter(p-> p > 10).mapToInt(i -> i).sum();
		
		System.out.println(testABC);
		
		
		//Java Stream doesn't store data
		//Only used for filtering data, but cannot modify the data itself..
		
		
		//Java SE 9 little thing
		//src having multiple package and each with module-info.java <--which describe for each package(module) , what is the java required ?
		/**
		 * 
		 * module com.mydeveloperplanet.jpmshello {		<--inside package com.mydeveloperplanet.jpmshello, it required java.base
		 *   requires java.base;
		 *}
		 * **/
		
		System.out.println("=====");
		
//		Stream.of(1,2,3,4,5,6,7,8,9,10).takeWhile(i -> i < 5 ).forEach(System.out::println);	- takeWhile applied in SE 9
		
		
//		System.out::println			:: <-- Method Reference 
//		Refer to Functional Interface
	}
	
	//cast return type must be T
	private static <T> Stream<T> testMe(Stream<T> currStream) {
		
		return currStream.map(o -> Optional.ofNullable(o))			//1. return a list of optional which is not null
				.filter(Optional::isPresent)		//2. filter the return which is present
				.map(Optional::get);		//3. get back the value
	}
}
