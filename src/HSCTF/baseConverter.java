package HSCTF;

import java.math.BigInteger;
import java.util.Scanner;

public class baseConverter{

    public static final String value = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String args[]){
        for(int base = 2;base<100;base++){
        	BigInteger m1 = new BigInteger(toBase10("BED97",base));
        	BigInteger m2 = new BigInteger(toBase10("CAF8DD",base));
        	BigInteger ans = new BigInteger(toBase10("C96DD777AB",base));
        	BigInteger result = m1.multiply(m2);
        	System.out.println(result+"=?"+ans);
        	if(result.equals(ans)){
        		System.out.println("Base:"+base);
        		break;
        	}
        }
    }
    public static String toBase10(String num, int from){
        long total = 0;
        int counter = num.length();
        char[] stringArray = num.toCharArray();
        for(char w : stringArray){
            counter--;
            total += value.indexOf(w)*Math.pow(from,counter);
        }
        return String.valueOf(total);
    }
    public static String newBase(String num, int to){
        String total = "";
        int current = 0;
        while(Integer.valueOf(num) > 0){
            current = Integer.valueOf(num)%to;
            total = value.charAt(current)+total;
            num = String.valueOf(Integer.valueOf(num)/to);
        }
    return total;
    }
}