package Java;

import java.util.List;

public class Utilities {
	public static void main(String[] args){
		print(new int[]{1,2,3});
	}
	public static void print(Object obj){
		System.out.println(obj);
	}
	public static void print(Object[] arr){
		for(Object obj:arr)
			print(obj);
	}
	public static void print(List<Object> lis){
		for(Object obj:lis)
			print(obj);
	}
}
