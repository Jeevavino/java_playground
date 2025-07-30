package com.java.playground;
import java.util.List;
import java.util.ArrayList;

public class RTExceptionTest {

	public static void main(String[] args) {


		int[] numbers = {1, 2, 3, 4, 100};
		List<String> list = new ArrayList<>();
        list.add("Record 1");
        list.add("Record 2");
        list.add("Record 3");
        list.add("Record 4");
        list.add("Record 5");
		
		try {     
			// Trying to access an index that is out of bounds
			System.out.println(numbers[5]); // This will throw because 5th index is not there
			System.out.println(list.get(5));// This will throw because 5th index is not there in the list
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e); // This will throw because 5th index is not there // you should handle here
			int length = numbers.length;
			System.out.println("Printing Array last record " + numbers[length-1]); //I got this record now
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e); // This will throw because 5th index is not there // you should handle here
			int size = list.size();
			System.out.println("Printing List last record " + list.get(size - 1)); //I got this record now
		}catch(Exception e) {
			System.out.println(e); // This will throw because 5th index is not there
			if(e instanceof ArrayIndexOutOfBoundsException) {
				//you won't have clue or clarity if you catch Super Class Exception
				
			}
		}

	}

}
