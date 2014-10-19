
public class stuuff {
	
	public static void main(String[] args){
		System.out.println(recursive(11));
	}
	
	public static String recursive(int i){
		if(i<=0||i==1)
			return "0";
		if(i%2==0)
			return i+" "+recursive(i-2)+" "+(i-1);
		return i-1+" "+recursive(i-2)+" "+i;
	}

}
