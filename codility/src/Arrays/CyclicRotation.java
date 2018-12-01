package Arrays;

import java.util.stream.IntStream;

public class CyclicRotation {

	public static void main(String[] args) {
		int[] array1 = {3,8,9,7,6};
		int seq1 = 3;
		
		int[] array2 = {1,2,3,4};
		int seq2 = 4;
		
		int[] array3 = {0,0,0};
		int seq3 = 1;
		
		solution(array2,seq2);
	}
	
	private static void solution(int[] A, int K)
	{
		if(A.length == 0)
			return;
			
		int[] orderedArr = new int[A.length];
		
		for(int i = 0 ; i < A.length ; i++)
		{
			orderedArr[(K+i)%A.length] = A[i];
		}
		
		System.out.println("======");
		
		IntStream.of(orderedArr).forEach(System.out::println);
	}
}
