import java.util.ArrayList;
import java.util.List;

public class MasterOrder {
    private ArrayList<CookieOrder> orders;
    
    public MasterOrder(){
        orders= new ArrayList<CookieOrder>();
    }
    public void addOrder(CookieOrder theOrder){
        orders.add(theOrder);
    }
    public int getTotalBoxes(){
        int sum=0;
        for(int i=0; i< orders.size(); i++){
            CookieOrder c=orders.get(i);
            sum= sum + c.getNumboxes();
        }
        return sum;
    }
    public int removeVariety(String cookieVar){
        int sum=0;
        int x=0;
        for(int i=orders.size()-1; i>=0; i--){
            CookieOrder c= orders.get(i);
            if(c.getVariety().equals(cookieVar)){
                x=c.getNumboxes();
                sum= sum+x;
                orders.remove(i);
            }
        }
        return sum;
    }
    public String toString(){
        String s= "";
        s= s+ orders;
        return s;
    }
}