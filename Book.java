package Java;

public class Book{
private String features;

public double getMatchCoeff(Book other){
	String[] a=new String[features.length()];
	String[] b=new String[features.length()];
	for(int i=0;i<features.length();i+=3){
		a[i/3]=features.substring(i,i+3);
		b[i/3]=other.features.substring(i,i+3);
	}
	int count=0;
	for(String feat:a)
		for(String otherFeat:b)
			if(feat.equals(otherFeat))
				count++;
	return count/(double)a.length;
}

/**create any other fields and/or methods that might be needed**/

}