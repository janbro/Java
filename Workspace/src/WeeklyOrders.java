import java.util.ArrayList;


public class WeeklyOrders {
    private ArrayList<DistributionOrder> comp;
    
    public WeeklyOrders(ArrayList<DistributionOrder> c){
        comp=c;
    }
    public void addDistribution(DistributionOrder d){
        for(int i=0; i<comp.size(); i++){
            if(d.getCompany().compareTo(comp.get(i).getCompany())<0){
                comp.add(i, d);
                return;
            }
        }
        comp.add(d);
    }
    public void cancelDistribution(DistributionOrder d){
        comp.remove(d);
    }
    public boolean alreadyPlacedOrder(String company){
        for(int i=0; i< comp.size(); i++){
            if(comp.get(i).getCompany().equals(company))
                return true;
        }
        return false;
    }
    public int numberOfOrders(String company){
        int x=0;
        if(alreadyPlacedOrder(company)==true){
            for(int i=0; i<comp.size(); i++){
                if(comp.get(i).getCompany().equals(company))
                    x++;
            }
        }
        return x;
    }
    public int totalNumOfBoxesOrdered(String company){
        int x=0;
        if(alreadyPlacedOrder(company)==true){
            for(int i=0; i<comp.size(); i++){
                if(comp.get(i).getCompany().equals(company))
                    x= x+ comp.get(i).getMasterOrder().getTotalBoxes();
            }
        }
        return x;
    }
    public int totalNumOfBoxesOrdered(){
        int x=0;
        for(int i=0; i<comp.size(); i++){
            x= x+ comp.get(i).getMasterOrder().getTotalBoxes();
        }
        return x;
    }
    public String toString(){
        String s="";
        for(int i=0; i<comp.size(); i++){
            s= s+ comp.get(i).getCompany() + " ";
        }
        return s;
    }
    
    public void modify(DistributionOrder d, CookieOrder c){
        d.getMasterOrder().addOrder(c);
    }
}
