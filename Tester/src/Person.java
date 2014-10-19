public class Person {
    private String name;
    private int age;
    private int numSameAge; 
    
    public Person(String theName, int theAge){
        name=theName; age = theAge; numSameAge=0;
    }
    public String getName(){ 
        return name; 
    }
    public int getAge(){ 
        return age;
    }
    public int getNumSameAge(){ 
        return numSameAge; 
    }
    public void setNumSameAge(int n) {
        numSameAge = n; 
    }
    public String toString(){
        return name + " " + age +"\n "+ 
        "There are "+ numSameAge + " people "
        + "with the same age as you in the family."+"\n";
     }
    public int compareTo(Person other){
        if(getAge()>other.getAge())
            return 1;
        else if(getAge()<other.getAge())
            return -1;
        return 0;
    }
}