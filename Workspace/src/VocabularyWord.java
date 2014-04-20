

public class VocabularyWord {
	private String word;
	private String definition;
	public VocabularyWord(String w, String d){
		word=w;
		definition=d;
}
	public String getWord(){
		return word;
	}
	public String getDefinition(){
		return definition; 
	}
	public String toString(){
		return word+":"+definition;
	}
}