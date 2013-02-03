package Java;

import java.util.*;

public class BlackJack {
	
	public static void main(String[] args){
//		boolean[] a=getPrimes(10);
//		for(boolean b:a)
//			System.out.println(b);
//		for(String a:getCards())
//			System.out.println(a);
		factorInfo(24);
		factorInfo(31);
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
	
	public static String[] getCards(){
		String[] suit={"Clubs", "Diamonds", "Hearts", "Spades"};
		String[] rank={"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		String[] cards=new String[suit.length*rank.length];
		for(int i=0;i<suit.length;i++)
			for(int j=0;j<rank.length;j++)
				cards[i*rank.length+j]=rank[j]+" of "+suit[i];
		return cards;
	}
	public static void factorInfo(int n){
		ArrayList<Integer> factors=new ArrayList<Integer>();
		factors.add(1);
		int num=n;
		for(int i=2;n>1;i++)
			if(n%i==0){
				factors.add(i);
				n/=i;
				i=2;
			}
		String not="";
		if(factors.size()>2)
			not="not ";
		System.out.println(num+" has "+factors.size()+" factors "+num+" is "+not+"prime.");
	}
}