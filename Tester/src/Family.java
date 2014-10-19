import java.util.ArrayList;
import java.util.List;

public class Family {
    private List<Person> peopleList;
    
    public Family(){ 
        peopleList = new ArrayList<Person>();
    }
    public void addPerson(Person p){
        //precondition: the peopleList field is sorted from youngest to oldest and all numSameAge are correct
        //postcondition: p has been added to peopleList in sorted order and all numSameAge fields are correct
        if(personBeforePos(p.getAge())==-1){
                peopleList.add(p);
                return;
        }
        if(peopleList.get(personBeforePos(p.getAge())).compareTo(p)==0){
            p.setNumSameAge(p.getNumSameAge()+1);
            peopleList.get(personBeforePos(p.getAge())).setNumSameAge(peopleList.get(personBeforePos(p.getAge())).getNumSameAge()+1);
        }
        peopleList.add(personBeforePos(p.getAge())+1,p);
    }
    private int personBeforePos(int age){
        if(peopleList.size()==0 || age<peopleList.get(0).getAge())
                return -1;
        //precondition: the peopleList field contains a list of Person sorted youngest to oldest
        //postcondition: reutrns the position in peopleList of the person in this family whose age is closest to 
        //the given age without being larger;
        //returns -1 if there are no people in this family at all, or none of whose age is less than or equal to 
        //the given age
        for(int i=0; i<peopleList.size(); i++){
            if(age<peopleList.get(i).getAge())
                return (i-1);
        }
        return peopleList.size()-1;
    }
    public String toString(){
        String s="";
        for(Person p: peopleList){
            s=s+ "Name: " + p.getName() +"\n"+
                    "Age: " + p.getAge() +"\n"+
                    "Number of People with Same Age: " + p.getNumSameAge()+"\n";
        }
        return s;
    }
    public void sort(ArrayList<Person> p){
        for(int i=0; i<p.size(); i++){
            Person small=p.get(i);
            for(int j=i; j<p.size(); j++){
                if(small.compareTo(p.get(j))==1)
                    small=p.get(j);
            }
            p.add(i, small);
        }
    }
}