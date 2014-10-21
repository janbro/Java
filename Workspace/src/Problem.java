public class Problem {
	
	public static void main(String[] args){
		System.out.println(a());
		System.out.println(b());
	}
	
	public static String a()
	{
		double b=0;
		for (double i=1;i<999999;i++)
		{
			b+=1/i/i;
		}
		b=Math.sqrt(b*6);
		return ""+b;
	}
	public static int b()
	{
		return a().indexOf("999999");
	}
}