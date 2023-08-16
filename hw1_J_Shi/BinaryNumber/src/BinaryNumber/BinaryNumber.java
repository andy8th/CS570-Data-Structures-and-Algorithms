package BinaryNumber;

import java.util.Arrays;

//Jince Shi
 
public class BinaryNumber {
	private int data[];
	private boolean overflow;
	
	// Constructors
	public BinaryNumber(int length) {
		if(length > 0) {
			this.data = new int[length]; }
	}
	 
	public BinaryNumber(String str) {
		boolean binarycheck = true; 
		int length = str.length(); 
		for(int i = 0; i < length; i ++)
		{
			if(str.charAt(i) != '0' && str.charAt(i) != '1') {
				System.out.println("Please input a Binary Number");
				binarycheck = false;
				break;
			}		
		}
		if(binarycheck == true) {
			data = new int[length];
			for(int i = 0; i < length; i++) {
				if(str.charAt(i)=='1'){
					data[i] = 1;
					}
				else {
					data[i] = 0;
					}
			}
		}
	}	
	
		public int getLength() {
		return data.length;
	}
	
		public int getDigit(int index) {
			if (index > data.length || index < 0) {
				System.out.println("Index out of bounds");
				return 0;
			} else {
				return data[index];
			}
		}
	
	public void shiftR(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Value can't be negative");
		}
		BinaryNumber littleendian = new BinaryNumber(data.length + amount);
		for (int i = amount; i < littleendian.getLength(); i++) {
			littleendian.data[i] = data[i - amount];
		}
		this.data = littleendian.data;
		System.out.println("Number after shift is: " + this.toString());
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		if (aBinaryNumber.getLength() != data.length) {
			System.out.println("Length should be the same");
		} else {
			System.out.print("Sum of " + aBinaryNumber + " and " + toString() + " is ");
			int carry = 0;
			int sum[] = new int[data.length];
			for (int i = 0; i < data.length; i++) {
				int result = carry + data[i] + aBinaryNumber.getDigit(i);
				if (result == 0) {
					sum[i] = 0;
					carry = 0;
				}
				if (result == 1) {
					sum[i] = 1;
					carry = 0;
				}
				if (result == 2) {
					sum[i] = 0;
					carry = 1;
				}
				if (result == 3) {
					sum[i] = 1;
					carry = 1;
				}
			}
			data = sum;
			if (carry == 1) {
				overflow = true;
			}
			System.out.println(toString());
		}
	}
	
	public String toString() {
		if(overflow == true) {
			return "Overflow";
		}
		else {
			String str ="";
			for(int i = 0; i<data.length; ++i) {
				str += data[i];
			}
			return str;
		}	
	}
	
	public int toDecimal() {
		int sum = 0;
		for(int i = 0; i< data.length; i++) {
			sum= (int)(sum + data[i] * Math.pow(2, i));
		}
		return sum;
	}
	
	public void clearOverflow() {
		overflow = false;
		System.out.println("Overflow is set False now");
	}
	
	public static void main(String[] args) {
		BinaryNumber b1 = new BinaryNumber("10110");
		BinaryNumber b2 = new BinaryNumber("11101");
		BinaryNumber b3 = new BinaryNumber("11100");
		BinaryNumber b4 = new BinaryNumber(4);
		BinaryNumber b5 = new BinaryNumber(5);
		BinaryNumber b6 = new BinaryNumber("101100");
		BinaryNumber b7 = new BinaryNumber("111010");
        System.out.println("Value = " + b4);
        System.out.println(b2 + "'s length is = " + b2.getLength());
        System.out.println(b5.toString());
		System.out.println(b2 + "'s Decimal Value is = " + b2.toDecimal());
		System.out.println("Digit at given index is = " + b2.getDigit(3));
		b2.shiftR(2);
        b1.add(b2);
		b1.add(b3);
		b6.add(b7);
	}
}