package Java.OldSchoolStuff;

public class ChineseCalendar extends ChineseZodiac{
	public String shortZodiac(int year){
		String a=toZodiacString(year);
		int comma=a.indexOf(",");
		int paren=a.indexOf("(");
		String animal=a.substring(0,comma);
		String element=a.substring(paren+1,a.length()-1);
		return animal+"/"+element;
	}
	public int getYear(int year, String animal, String element){
		boolean P=false,Q=false;
		for(String a:animals)
			P=P||a.equals(animal);
		for(String a:elements)
			Q=Q||(a.indexOf(element)>-1);
		if(!P||!Q)
			throw new IllegalArgumentException();
		int a=0,b=0;
		while(!animals[a].equals(animal))
			a++;
		while(!(elements[b].indexOf(element)>-1))
			b++;
		int y=0;
		for(int i=0;y<year;i++)
			y=a+i*animals.length+b+i*elements.length;
		return y;
	}
}