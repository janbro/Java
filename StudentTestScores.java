package Java;

import static Java.Statistics.*;

public class StudentTestScores {
	private String[] students;
	private int[][] scores;
	public StudentTestScores(){
		StudentTestScores(new String[]{"Alpha","Beta","Gamma","Delta","Epsilon","Zeta"},
						  new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
	}
	private void StudentTestScores(String[] students,int[][] scores){
		this.students=students;
		this.scores=scores;
	}
	public void printSummary(){
		for(int i=0;i<students.length;i++){
			System.out.print(students[i]);
			int[] grades=scores[i];
			for(int j=0;j<grades.length;j++)
				System.out.print("Test"+(j+1)+": "+grades[j]);
			System.out.println(getMean(grades));
		}
	}
	public void printLow(){
		int[] lows=new int[students.length];
		for(int i=0;i<students.length;i++){
			int[] grades=scores[i];
			int low=grades[0];
			for(int grade:grades)
				if(grade<low)
					low=grade;
		}
		int best=0;
		for(int i=0;i<students.length;i++)
			if(lows[i]<lows[best])
				best=i;
		System.out.println(students[best]);
	}
	public void printHigh(){
		int[] lows=new int[students.length];
		for(int i=0;i<students.length;i++){
			int[] grades=scores[i];
			int low=grades[0];
			for(int grade:grades)
				if(grade<low)
					low=grade;
		}
		int best=0;
		for(int i=0;i<students.length;i++)
			if(lows[i]<lows[best])
				best=i;
		System.out.println(students[best]);
	}
	public double avg(){
		double[] avgs=new double[students.length];
		for(int i=0;i<students.length;i++)
			avgs[i]=getMean(scores[i]);
		return getMean(avgs);
	}
}
