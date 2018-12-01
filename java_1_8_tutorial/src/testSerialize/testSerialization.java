package testSerialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import serializeObjTest.Employee;

public class testSerialization {

	public static void main(String[] args) {
		
		
		System.out.println(new BigDecimal("0.00").compareTo(new BigDecimal("0.00")));
		
		String test = "CLOS REGIONAL ASSISTANT 08";
		
		System.out.println("CLOS DEPUTY GENERAL MANAGER ASS 06".contains("ASST") || false);
		
		Employee emp = new Employee();
		emp.setName("Noob Noob");
		emp.setAge(21);
		emp.setJobPosition("Quanlity Analyst");
		emp.setSalary(new BigDecimal("12.98989"));
		
		//output stream
		try {
			FileOutputStream fs = new FileOutputStream("test");
			ObjectOutputStream oos = new ObjectOutputStream(fs);
			
			oos.writeObject(emp);
			fs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//input stream
		try {
			FileInputStream fis = new FileInputStream("test");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Object obj = ois.readObject();
			ois.close();
			//print
			Employee newEmp = (Employee)obj;
			
			System.out.println("test new emp : " + newEmp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
