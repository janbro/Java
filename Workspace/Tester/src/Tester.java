import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	public static ArrayList<Integer> marble = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		String[] nums = new String[2];
		input = reader.readLine();
		int caseNum=1;
		PrintWriter pr = new PrintWriter(System.out);
		while(true){
		if (input.length() > 1)
			nums = input.split(" ");
		input = reader.readLine();
		for (int i = 0; i <Integer.parseInt(nums[0]); i++) {
			//sortAdd(Integer.parseInt(input));
			marble.add(Integer.parseInt(input));
			input = reader.readLine();
		}
		Object[] sorted=marble.toArray();
		Arrays.sort(sorted);
		marble.clear();
		for(int i=0;i<sorted.length;i++)
			marble.add((int)sorted[i]);
		pr.println("CASE# "+caseNum+":");
		for (int i = 0; i < Integer.parseInt(nums[1]); i++) {
			if (marble.contains(Integer.parseInt(input)))
				pr.println(input + " found at " + (marble.indexOf(Integer.parseInt(input))+1));
			else
				pr.println(input + " not found");

			if(i<Integer.parseInt(nums[1])-1)
				input = reader.readLine();
		}
		marble.clear();
		nums=null;
		String temp;
		if((temp=String.valueOf((char)reader.read())).equals("0")){
			pr.flush();
			pr.close();
			reader.close();
			System.exit(0);
		}
		input=temp+reader.readLine();
		caseNum++;
		}
	}
}
