package com.test2;

public class A {

	public static void main(String[] args) {
		//A a = new A();
		//System.out.println(a);
		long startTime = System.currentTimeMillis();
		//START.....your program....
		int Value = 0;
		int Count = 0;
		            for (int i = 1; ; i++){
		                Count = 0;
		                for (int j = 2; j <= 10; j++){
		                    if (i % j == 1)
		                    {
		                        Count++;
		                        Value = i;
		                    }
		                }
		                if (Value % 11 == 0 & Count == 9)
		                {
		                    //Console.WriteLine(i);
		                	System.out.println(i);
		                    break;
		                }
		            }
		//END.....your program....
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
	}
	public void gcd(int a, int b){
		int t;
		while (b!=0){
			t= a;
			b= a % b;
			a=t;
			
		}
	}

}
