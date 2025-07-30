package com.java.playground;

//Custom runtime exception
class CustomRuntimeException extends RuntimeException {
 public CustomRuntimeException(String message) {
     super(message);
 }
}

 class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }
}
//you will custom exception for your use case, it is not a predifined exception. 
public class CustomExceptionTest {

	public static void main(String[] args) {
		  try {
	            // Triggering the custom checked exception
	            throw new CustomCheckedException("This is a custom checked exception");
	        } catch (CustomCheckedException e) {
	            System.out.println("Caught checked exception: " + e.getMessage());
	        }

	        // Triggering the custom runtime exception
	        throw new CustomRuntimeException("This is a custom runtime exception");

	}

}
