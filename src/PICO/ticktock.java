package PICO;

import java.math.BigInteger;

public class ticktock {
	
	public static void main(String[] args){
		int nums[][] = {{1, 2}, {2, 3}, {8, 13}, {4, 29}, {130, 191}, {343, 397}, {652, 691}, {858, 1009},
		           {689, 2039}, {1184, 4099}, {2027, 7001}, {5119, 10009}, {15165, 19997}, {15340, 30013},
		           {29303, 70009}, {42873, 160009}, {158045, 200009}};
		BigInteger bg = new BigInteger("10000000000000000000000000000000000000000000000000000000");
		for(BigInteger i=new BigInteger("1");i.compareTo(bg)<0;i=i.add(new BigInteger("1"))){
			BigInteger num = new BigInteger("200009").multiply(i).add(new BigInteger("158045"));
			boolean goodNumber = true;
			for(int j=0;j<nums.length;j++){
				if(!num.mod(new BigInteger(""+nums[j][1])).equals(new BigInteger(""+nums[j][0]))){
					goodNumber = false;
				}
			}if(num.mod(new BigInteger("100000")).equals(new BigInteger("0"))){
				System.out.println("Curr:"+num);
			}
			if(goodNumber){
				System.out.println("GOOOOOOOOOOD"+num);
			}
		}
	}

}
