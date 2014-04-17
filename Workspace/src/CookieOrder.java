public class CookieOrder {
    private String v;
    private int b;
    
    public CookieOrder(String variety, int numBoxes){
        v=variety;
        b=numBoxes;
    }
    public String getVariety(){
        return v;
    }
    public int getNumboxes(){
        return b;
    }
    public String toString(){
        return v + ": " + b;
    }
}