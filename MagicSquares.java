package pakage;

import java.util.ArrayList;

public class MagicSquares {

	public static void main(String[] args){
		int magicNum=50*50;
		int t=0,p=0,temp=magicNum;
		while(temp>0){
			temp/=10;
			p++;
		}
		int[][] ms = magicSquare(magicNum);
		for(int i=0;i<ms.length;i++){
			for(int j=0;j<ms[i].length;j++){
				System.out.print(ms[i][j]+" ");
				temp=ms[i][j];
				while(temp>0){
					temp=temp/10;
					t++;
				}
				for(int l=0;l<p-t;l++){
					System.out.print(" ");
				}t=0;
			}
			System.out.println();
		}
	}

	public static int[][] magicSquare(int num){
		int[][] res=new int[(int) Math.sqrt(num)][(int) Math.sqrt(num)];
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=1;i<=num;i++)
			nums.add(i);
		System.out.println(nums);
		int midNum=(num+1)/2;
		int magicNum=(int) ((res.length*(num+1))/2);
		int x=0,y=res.length/2,count=0;
		//Evens
		while(count<=num-1){
			if(res[x][y]==0){
				res[x][y]=nums.get(count);
				count++;
				x--;
				y++;
			}
			else{
				x+=2;
				y--;
			}
			if(x<0)
				x=res.length-1;
			if(x>=res.length)
				x=x-res.length;
			if(y<0)
				y=res.length-1;
			if(y>=res.length)
				y=y-res.length;
		}
		System.out.println("Magic Num is: "+magicNum);
		return res;

	}

}
