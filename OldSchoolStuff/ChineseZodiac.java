package Java;

public class ChineseZodiac {
	private static final int startYear=1924;
	protected static final String[] animals = {"rat","ox","tiger","rabbit",
			"dragon","snake","horse","sheep","monkey","chicken","dog","pig"};
	protected static final String[] elements ={"elder wood (fir)","younger wood (bamboo)",
			"elder fire (burning wood)","younger fire (lamp flame)","elder earth (hill)",
			"younger earth (plain)","elder metal (weapon)", "younger metal (kettle)",
			"elder water (wave)", "younger water (brook)"};
	public static String toZodiacString(int year){
		year-=startYear;
		String animal=animals[year%animals.length];
		String element=elements[year%elements.length];
		return animal+","+element;
	}
	public static void main(String[] args){
		System.out.println(toZodiacString(1925));
		System.out.println(new ChineseCalendar().shortZodiac(1949));
		System.out.println(toZodiacString(1936));
		System.out.println(new ChineseCalendar().getYear(1924, "ox", "younger earth (plain)"));
	}

}