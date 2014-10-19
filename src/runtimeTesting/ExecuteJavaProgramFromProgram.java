package runtimeTesting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ExecuteJavaProgramFromProgram {
	
	public static void main(String[] args) throws IOException{
		
		Process cat = Runtime.getRuntime().exec("java Tester hello");
		BufferedReader catOutput= new BufferedReader(new InputStreamReader(cat.getInputStream()));
		System.out.println(catOutput.readLine());
	}

}
