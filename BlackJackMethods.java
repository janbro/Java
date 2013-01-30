package Java;

public class BlackJackMethods {
	public static void main(String[] args){
		boolean[] a=getPrimes(10);
		for(boolean b:a)
			System.out.println(b);
	}
	public static boolean[] getPrimes(int n){
        boolean[] isPrime=new boolean[n+1];
        for(int i=0;i<=n;i++) 
            isPrime[i]=true;
        for(int i=2;i*i<=n;i++)
            if(isPrime[i])
                for(int j=i;i*j<=n;j++)
                    isPrime[i*j]=false;
        return isPrime;
	}
}
