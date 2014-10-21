import java.awt.List;
import java.util.ArrayList;


public class XOR {
	
	public static void main(String[] args){
		String in = "5";
		String in2 = "2";
		char[] kl = in2.toCharArray();
		ArrayList<Character> as = new ArrayList<Character>();
		for(int i=0;i<kl.length;i++)
			as.add(in.charAt(i));
		String res="";
		for(char a:in.toCharArray()){
			res+=(a^as.get(0));
			as.remove(0);
		}System.out.println(res);
	}

}
