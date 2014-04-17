import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class newPob {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(br.readLine());
		//int[] arr
		for(int i=0; i<times; i++){
			String[] input=br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int n = Integer.parseInt(input[1]);
			System.out.println(a*n);
		}
	}
}
