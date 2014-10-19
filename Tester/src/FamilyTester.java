public class FamilyTester {
    public static void main(String[] args) {
        Person dad = new Person("Billy Bob",42);
        Person mom = new Person("Mary Bob", 39);
        Person aunt= new Person("Aunt Jane", 39);
        Person uncle = new Person("Uncle Joe", 43);
        
        Family munsters = new Family();
        munsters.addPerson(mom);
        munsters.addPerson(dad);
        munsters.addPerson(uncle);
        munsters.addPerson(aunt);
        System.out.println(munsters.toString());
        String s="";
        s+="hello";
        System.out.println(s);
        }
}