package HSCTF;

public class Applicant implements Comparable{
		private int start;
		private int end;
		private int pay;
		
		public Applicant(int start, int end, int pay){
			this.start = start;
			this.end = end;
			this.pay = pay;
		}
		
		public int getStart(){
			return this.start;
		}
		
		public int getEnd(){
			return this.end;
		}
		
		public int getPay(){
			return this.pay;
		}
		
		public int compareTo(Object obj){
			Applicant other = (Applicant)obj;
			if(this.start<other.getStart())
				return -1;
			else if(this.start>other.getStart())
				return 1;
			else{
				if(this.start<other.getStart())
					return -1;
				else if(this.start>other.getStart())
					return 1;
				else
					return 0;
			}
		}

	}