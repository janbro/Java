import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OneTimePad {
	

	static ArrayList<String> engDict = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/Eng2.txt")));
		String line = "";
		while((line = br.readLine())!=null){
			if(line.length()>3)
				engDict.add(line.toLowerCase());
		}br.close();
		System.out.println("Dictionary Made");
		//SQUMS
		
		decrypter();

	}
	public static int wrap(int i,int range){
		if(i>=0&&i<range)
			return i;
		if(i>=range)
			return i%range;
		return range+i;
	}
	
	 public static String decrypter(){
	        char array[] = new char[5];
	        String password = new String();

	        for (char c0 = 'A'; c0 <= 'Z'; c0++) {
	            array[0] = c0;
	            for (char c1 = 'A'; c1 <= 'Z'; c1++) {
	                array[1] = c1;
	                for (char c2 = 'A'; c2 <= 'Z'; c2++) {
	                    array[2] = c2;
	                    for (char c3 = 'A'; c3 <= 'Z'; c3++) {
	                        array[3] = c3;
	                        for (char c4 = 'A'; c4 <= 'Z'; c4++){
	                            array[4] = c4;
	                            String s = new String(array);
	                            password = s;
	                    		String in = "KIFC".toLowerCase().replaceAll(" ", "");
	                    		String in2 = "HHHS".toLowerCase().replaceAll(" ", "");
	                            String key = password.toLowerCase();
	                    		String res = "";
	                    		String res2 = "";
	                    		for(int l=0;l<in.length();l++){
	                    			int f = in.charAt(l)-97;	
	                    			int p = key.charAt(l%key.length())-97;
	                    			res+=((char)(wrap(f-p,26)+97));
	                    		}//System.out.println(res);
	                    		for(String check:engDict){
	            					if(check.equals(res)){
	            						for(int l=0;l<in2.length();l++){
	    	                    			int f = in2.charAt(l)-97;	
	    	                    			int p = key.charAt(l%key.length())-97;
	    	                    			res2+=((char)(wrap(f-p,26)+97));
	    	                    		}System.out.println("Word!"+res);
		            							System.out.println("Key:"+key+" - Words:"+res+":"+res2);
	            					}
	            				}
	                        }
	                    }
	                }
	            }
	        }
	        return password;
	    }
}
