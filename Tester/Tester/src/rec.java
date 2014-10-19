
public class rec {
	public static void main(String[] args){
		System.out.println(count(20));
	}
	
	public static String count(int num){
		if(num==0)
			return "0";
		return count(num-1)+" "+num;
	}
}
