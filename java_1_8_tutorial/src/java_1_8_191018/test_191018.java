package java_1_8_191018;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import serializeObjTest.Employee;
import serializeObjTest.EmployeeModel;

public class test_191018 {

	public static void main(String[] args) {
		
		EmployeeModel model = new EmployeeModel();
		
		model.setName("test_one");
		model.setAge("21");
		model.setCompanyName("test_one_company");
		model.setJobPosition("job_position_test_one");
		model.setSalary("150000.00");
		
		EmployeeModel model2 = new EmployeeModel();	
		
		model2.setName("test_two");
		model2.setAge("21");
		model2.setCompanyName("test_two_company");
		model2.setJobPosition("job_position_test_two");
		model2.setSalary("25000.00");
		
		List<EmployeeModel> modelList = new ArrayList<EmployeeModel>();
		
		modelList.add(model);
		modelList.add(model2);
		
		// testing me
		List<Employee> employeeList = new ArrayList<Employee>();
		
		//using in general getter and setter?
		//foreach only accept one ?
		//reflection does not indicate anything if there is any field changes
		modelList.forEach(lambdaModel -> {
			Employee employee = new Employee();
			
			employee.setName(lambdaModel.getName());
			employee.setAge(Integer.parseInt(lambdaModel.getAge()));
			employee.setCompanyName(lambdaModel.getCompanyName());
			employee.setJobPosition(lambdaModel.getJobPosition());
			employee.setSalary(new BigDecimal(lambdaModel.getSalary()));
			
			employeeList.add(employee);
		});
		
		
		Collections.sort(modelList, (o1,o2)->{
			return o1.getName().compareTo(o2.getName());
		});
		
		modelList.sort(Comparator.comparing(EmployeeModel::getName));		//<--- This one is the new syntax
		
		//test thenComparing (if getAge is the same, then comparing getname)
		employeeList.sort(Comparator.comparingInt(Employee::getAge).thenComparing(Employee::getName));
		
		Function<Double,Double> test = TestPow::square;
		Function<Double,Double> test2 = TestPow::square;
		
		Double result = test.andThen(test2).apply(2d);
		
		System.out.println(result);
		
		
		//	//		//
		//		//		//
		//	//		//
		//		//		//
		Integer testFunction = testFunction(12, x-> {
			return (x+1);
		});
		
		System.out.println(testFunction);
		
		
		//consumer -- consume and no return any value, like lambda x-> System.out.println(x);
		//predicate --consume and return a boolean value, accept lambda argument, 
			//eg. Predicate<Integer>		
			//	(x) -> { x == 3)
		//
		
		int[] abc = {10,9,6,3,1,2,2,1,1};
		
		int[] cde = IntStream.of(abc).sorted().toArray();
		long countDistinct = IntStream.of(abc).distinct().count();	
		
		IntStream.of(cde).forEach(System.out::println);
		
		System.out.println(countDistinct);
		
	}
	
	private static final <T> T testFunction(T valToModify, Function<T,T> function){
		return function.apply(valToModify);
	}
	
	
	static final class TestPow {
		 public static double square(double num){
		        return Math.pow(num, 2);
		 }
	}
}
