package Iterations;

import java.util.Arrays;

public class BinaryGap {

	public static void main(String[] args) {
		
		int case1 = 9;
		int case2 = 1041;
		int case3 = 32;
		int case4 = 17;
		
		solution(case3);
	}
	
	//get binary number of string
	//String[] == List<String> <-depend on wat u want
	//UNDERSTANDING :
	// == is used in situation whereby comparing with primitive type value
	// .equals is safe with String representation as it is an object type
	public static void solution(int num)
	{
		String binaryStr = Integer.toBinaryString(num);	//Integer
		String[] binaryStrList = binaryStr.split("");
		
		int largestGap = 0;	//required a gap collection.
		int gapCollection = 0;
		
		for(int i = 0 ; i < binaryStrList.length ; i++)
		{
			if(binaryStrList[i].equals("1"))	
			{
				largestGap = Math.max(gapCollection, largestGap);
				gapCollection = 0; //recount the next gap
			} else {
				gapCollection++;
			}
		}
		
		System.out.println(largestGap);
	}
}
