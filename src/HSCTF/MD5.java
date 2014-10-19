package HSCTF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.util.ArrayList;
import java.util.Formatter;

public class MD5 {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/HSCTF/adjectives.txt")));
		String line = "";
		ArrayList<String> adjectives = new ArrayList<String>();
		while((line = br.readLine())!=null){
			adjectives.add(line.toLowerCase().replaceAll(" ", "").replaceAll("\t", ""));
		}br.close();
		System.out.println("Adj Dictionary Made");
		
		br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/HSCTF/colors.txt")));
		line = "";
		ArrayList<String> colors = new ArrayList<String>();
		while((line = br.readLine())!=null){
			colors.add(line.toLowerCase().replaceAll(" ", "").replaceAll("\t", ""));
		}br.close();
		System.out.println("Colors Dictionary Made");
		
		br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/HSCTF/animals.txt")));
		line = "";
		ArrayList<String> animals = new ArrayList<String>();
		while((line = br.readLine())!=null){
			animals.add(line.toLowerCase().replaceAll(" ","").replaceAll("\t", ""));
		}br.close();
		System.out.println("Animals Dictionary Made");
		
		int count=0;
		for(int i=0;i<=9;i++){
			for(String adj:adjectives){
				for(String color:colors){
					for(String pluralCommAnimal:animals){
						//System.out.println("N:"+color+":"+pluralCommAnimal);
						String str = "HSCTF"+i+adj+color+pluralCommAnimal;
						if(count%1000000==0)
							System.out.println("Tried: "+count+" passwords. On "+str);
						//System.out.println("STRING: "+str);
						byte[] bytesOfMessage = str.getBytes("UTF-8");
			
						MessageDigest md = MessageDigest.getInstance("MD5");
						byte[] thedigest = md.digest(bytesOfMessage);
						Formatter formatter = new Formatter();
						for (byte b : thedigest) {
							formatter.format("%02x", b);
						}
						String hex = formatter.toString();
						formatter.close();
						//System.out.println(hex);
						if(hex.equals("0c283432b2ef5adea008bcd7c27f4c3f")){
							System.out.println("DONE!"+str);
							return;
						}count++;
					}
				}
			}
		}System.out.println("Not Found!");
	}
}
