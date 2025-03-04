package com.pujitha.playground;

import java.io.IOException;

class ExecTest {
	
	public void exe() throws IOException {
		throw new IOException();
	}
}

public class ExceptionTest {

	public static void main(String[] args) throws IOException {
		//throw new NullPointerException(); // NO Problem. because it is runtimeexcpetion. not forced to handle the Exception. 
		//throw new IOException();//Checked exception . forced
		ExecTest et = new ExecTest();
		et.exe();
		

	}

}


