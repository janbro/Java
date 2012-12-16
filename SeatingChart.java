package Java;

public class SeatingChart {
	private String[][] theChart;
	public SeatingChart(int r,int c){
		theChart=new String[r][c];
		clearAllSeats();
	}
	public SeatingChart(String[][] chart){
		theChart=chart;
	}
	public void clearAllSeats(){
		for(String[] a:theChart)
			for(String b:a)
				b="";
	}
	public void setSeat(int r,int c,String name){
		theChart[r][c]=name;
	}
	public void swap(int r1,int c1,int r2, int c2){
		String x=theChart[r1][c1];
		theChart[r1][c1]=theChart[r2][c2];
		theChart[r2][c2]=x;
	}
	public boolean contains(String s){
		for(String[] a:theChart)
			for(String b:a)
				if(s.equals(b))
					return true;
		return false;
	}
	public int[] getCell(String s){
		for(int i=0;i<theChart.length;i++)
			for(int j=0;j<theChart[i].length;j++)
				if(s.equals(theChart[i][j]))
					return new int[]{i,j};
		return null;
	}
	public boolean swap(String s1,String s2){
		if(!contains(s1) || !contains(s2))
			return false;
		int[] a=getCell(s1);
		int[] b=getCell(s2);
		theChart[a[0]][a[1]]=s2;
		theChart[b[0]][b[1]]=s1;
		return true;
	}
	public int getRow(String s){
		if(!contains(s))
			return -1;
		for(int i=0;i<theChart.length;i++)
			for(String b:theChart[i])
				if(s.equals(b))
					return i;
		return -1;
	}
	public int getCol(String s){
		if(!contains(s))
			return -1;
		for(String[] a:theChart)
			for(int j=0;j<a.length;j++)
				if(s.equals(a[j]))
					return j;
		return -1;
	}
	public String get(int r,int c){
		return theChart[r][c];
	}
	public String toString(){
		String s="";
		for(String[] a:theChart){
			for(String b:a)
				s+=b+", ";
			s+="\n";
		}
		return s;
	}
}