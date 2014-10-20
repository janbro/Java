public class DistributionOrder {
    private String company;
    private MasterOrder masterOrder;
    
    public DistributionOrder(String c, MasterOrder mo){
        company=c;
        masterOrder=mo;
    }
    public String getCompany(){
        return company;
    }
    public MasterOrder getMasterOrder(){
        return masterOrder;
    }
    public String toString(){
        return company + masterOrder;
    }
}