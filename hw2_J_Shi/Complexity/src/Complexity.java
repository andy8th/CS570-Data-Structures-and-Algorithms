//Jince Shi HW2

public class Complexity {

    static int total = 0;	
	
	// Method 1: Time Complexity O(n^2).
	
	public static void method1(int n) {
		int counter = 0;
		for ( int i = 0; i < n; i++) {
			for ( int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++; 
				}
			   }
		System.out.println("Time complexity O(n^2) is : " + counter);
	 }
	
	// Method 2: Time Complexity O(n^3)
	 public static void method2(int n) {
		 int counter = 0;
		 for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						System.out.println("Operation " + counter);
						counter++;
					}
				}
		 	}
		 System.out.println("Time complexity O(n^3) is: " + counter);
	 }
	 // Method 3: Time Complexity O(log n)
	 public static void method3(int n) {
		 int counter = 0;
		 for (int i = 1; i < n; i = i * 2) {
			 System.out.println("Operation " + counter); // Counted number of operations performed
			 counter++;
		 }
		 System.out.println("Time complexity O(log n) is: " + counter);
	 }
	 //Method 4: Time Complexity O(n log n):
	 public static void method4(int n) {
		 int counter = 0;
		 for (int i = 0; i < n; i++) {
			    for(int j = 1; j <= n; j = j * 2) {
			    	System.out.println("Operation " + counter);
			    	counter++;
			    }
		}
		 System.out.println("Time complexity O(nlogn) is: " + counter);
	 }
	 
	//Method 5: Time Complexity with O(log log n).
		 public static void method5(int n) {
			 int counter = 0;
			 for (int i = 2; i < n; i = i * i) {
				 System.out.println("Operation " + counter);
				 counter++;
			}
			 System.out.println("Time complexity O(loglogn) is: " + counter);
		 }
		 //Method 6: Time Complexity O(2^n).
		 public static int method6(int n) {
			
			 if (n <= 1) {
		            total++;
		            System.out.println("Operation " + total);
		            return n;
		        }
		        total++;
		        System.out.println("Operation " + total);
		        return method6(n -1 ) + method6(n-1);
		 }
			
	public static void main(String[] args) {
		int n= 10;
		method1(n);
		method2(n);
		method3(n);
		method4(n);
		method5(n);
		method6(n);

	}
	}

