package com.java.playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckedExceptionTest {

	public static void main(String[] args) {
		File file = new File("example.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            //Something wrong. Handle file from Differnt location. 
        } catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		} finally { // this will be always executed 100% without doubt
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
