package com.test2;

public class The7Up {

	public static void main(String[] args) {
/*		int n=6;
		int count=1; 
		int pos = 1;
		while(true){
			
			
			System.out.println("pos "+pos+" count = "+count);
			count++;
			pos++;
			if(count ==7)
				break;
		}*/
		int n=10;
/*		
 *digit triangle
   		for(int i=1;i<=n;i++){
			for(int k=1;k<=n-i;k++)
				System.out.print(" ");
			for(int j=1;j<=i;j++){
				System.out.print(j+" ");
			}
			System.out.println();
		}
*/

		//Pascal's triangle
/*		for(int i=1;i<=n;i++){
			for(int k=1;k<=n-i;k++)
				System.out.print(" ");
			
			int val =1;
			for(int j=1;j<=i;j++){
				//System.out.print(j+" ");
				System.out.print(val+" ");
				val = val * (i-j)/j;
			}
			System.out.println();
		}*/
		
		//fibonacci 
		int first=0 ,second=1,next;
		for(int i=0;i<n;i++){
			 if(i<=1){
				next=i;
			 }else{
				 next = first + second;
				 first = second;
				 second = next;
			 }
			 System.out.print(next+" ");
		}
		
		System.out.println();
	}

}
