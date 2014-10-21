package HSCTF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTP {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/Eng2.txt")));
		String line = "";
		ArrayList<String> engDict = new ArrayList<String>();
		while((line = br.readLine())!=null){
			if(line.length()<=4)
				engDict.add(line.toLowerCase());
		}
		System.out.println("Dictionary Made");
		
		String ciphertetxt = "FF XRPQF UR TJBSO GYTSR YFXIN SI HEGOKZB EPICZWLN".replace(" ", "");
		String word = "KIFC".toLowerCase();
		String word2 = "HHHS".toLowerCase();
		System.out.println(encode("rxep","ihvw"));//ihvw:makc:tgmq:uetb:xmgi:
		//String in = "kzb";
		//String key = "ipm";
		char[] array=new char[4];
		for (char c0 = 'A'; c0 <= 'Z'; c0++) {
            array[0] = c0;
            for (char c1 = 'A'; c1 <= 'Z'; c1++) {
                array[1] = c1;
                for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                    array[2] = c2;
                    for (char c3 = 'A'; c3 <= 'Z'; c3++) {
                        array[3] = c3;
                String key = new String(array).toLowerCase();
                String res = encode(word,key);
                String res2 = encode(word2,key);
                for(String check:engDict){
					if(check.toLowerCase().equals(res)){
						for(String check2:engDict)
						{
							if(check2.toLowerCase().equals(res2)){
								System.out.println("Word!"+res);
    							System.out.println("Key:"+key+" - Words:"+res+":"+res2);
							}
						}
					}
                }
            }
        }
            }
		}
	}
	
	public static String encode(String input, String key){
		String res="";
		for(int l=0;l<input.length();l++){
			int f = input.charAt(l)-97;	
			int p = key.charAt(l%key.length())-97;
			res+=((char)(wrap(f+p,26)+97));
		}
		return res;
	}
	public static String decode(String input, String key){
		String res="";
		for(int l=0;l<input.length();l++){
			int f = input.charAt(l)-97;	
			int p = key.charAt(l%key.length())-97;
			res+=((char)(wrap(f-p,26)+97));
		}
		return res;
	}
	public static int wrap(int i,int range){
		if(i>=0&&i<range)
			return i;
		if(i>=range)
			return i%range;
		return range+i;
	}

}
