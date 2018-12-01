package test_node;

import java.util.stream.Stream;

public class test031018 {
	
	public static void main(String[] args) {
		
//		BigDecimal no1 = new BigDecimal("10000000.00");X
//		BigDecimal no2 = new BigDecimal("66.666666670000");
//		
//		System.out.println(no1.divide(no2,10,RoundingMode.HALF_UP));
		Stream<String> test = Stream.generate(()-> {return "abc";});
		test.forEach(a -> System.out.println(a));
		
		
		
//		int[] case1 = {2,3,1,4,5}; //3   --> 2,3,1,4,5
//		int[] case2 = {4,2,1,3,5}; //3   --> 2,4,1,3,5
//		int[] case3 = {4,5,1,3,2}; //2
//		int[] case4 = {2,3,1,5,4}; //2
//		int[] case5 = {2,3,4,5,1}; //1
//
//		List<Integer> case6List = IntStream.range(1, 10).boxed().collect(Collectors.toList()); //boxing int primitive reference Integer
//		Collections.shuffle(case6List); //shuffling
//		
//		int[] case6 = case6List.stream().mapToInt(i->i).toArray();	//unboxing imba
//		
//		for(int i : case6)
//		{
//			System.out.print(i + ", ");
//		}
//		
//		testSolution1(case6);
	}
	
	private static void testSolution1(int[] arr)
	{
		int[] newArr = new int[arr.length];
		int turnedCount = 0;
		int isTurnedCount = 0;
		int minValue = (isTurnedCount + 1) ;
		
		for(int i = 0; i < arr.length ; i++)
		{
			//put one into the newArr
			int currentVal = arr[i];
			
			if(currentVal <= arr.length)
			{
				newArr[(currentVal - 1)] = 1;
				
				if(currentVal == minValue)
				{
					turnedCount++;
					
					//extra condition to check when min value is turned, the next sequential value will turn or not
					int j = isTurnedCount;
					while(j < newArr.length)
					{
						if(newArr[j] == 1)
						{
							isTurnedCount++;
						}else {
							break;		//as long as meet 0 in the way, break from the loop
						}
						
						j++;
					}
				}
				
				if(isTurnedCount > 0)
				{
					minValue = (isTurnedCount + 1) ;	// ignore the light bulb that has edy turned on together when min value turned on
				}
			}
		}
		
		System.out.println("Turned Count : " + turnedCount);
	}

}
