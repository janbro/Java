import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class SerPounce {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(System.out);
		String good[] = {"Meow","Mew","Purr"};
		String bad[] = {"Hiss","Scratch"};
		int testcases=0;
		int casenum = 0;
		while((testcases= Integer.parseInt(br.readLine()))!=0){
			int lastnumExc=0;
			int bestdays=0;
			int days=0;
			casenum++;
			for(int i=0;i<testcases;i++){
				String in=br.readLine();
				boolean had = false;
				boolean gooded = false;
				for(String g:good){
					int exc = 0;
					if(in.indexOf("!")!=-1)
						exc = (in.length()-in.indexOf("!"));
					boolean equals = false;
					if(exc==0)
						equals = in.equals(g);
					else
						equals = in.substring(0,in.indexOf("!")).equals(g);
					if(equals)
						gooded = true;
					if(equals&&exc>=lastnumExc){
						days++;
						had=true;
						break;
					}
				}
				if(!had)
					days=0;
				if(gooded&&!had)
					days=1;

				if(in.indexOf("!")!=-1)
					lastnumExc = (in.length()-in.indexOf("!"));
				else
					lastnumExc=0;
				if(days>bestdays)
					bestdays=days;
			}pr.println("Case "+casenum+": Ser Pounce has consistently been adorable and loud for "+bestdays+" day(s) in a row.");
		}pr.flush();
	}

}
