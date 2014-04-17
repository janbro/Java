import java.util.ArrayList;

public class CookieTester {
    public static void main(String[] args){
        CookieOrder a= new CookieOrder("Chocolate Chip", 1);
        CookieOrder b= new CookieOrder("Shortbread", 5);
        CookieOrder c= new CookieOrder("Macaroon", 2);
        CookieOrder d= new CookieOrder("Chocolate Chip", 3);
        
        a.getVariety();        b.getVariety();        c.getVariety();        d.getVariety();
        a.getNumboxes();    b.getNumboxes();    c.getNumboxes();    d.getNumboxes();
        
        ArrayList<CookieOrder> x=new ArrayList<CookieOrder>();
            x.add(a);
            x.add(b);
            x.add(c);
            x.add(d);
        MasterOrder mm=new MasterOrder();
            mm.addOrder(a);
            mm.addOrder(b);
            mm.addOrder(c);
            mm.addOrder(d);
        DistributionOrder dd= new DistributionOrder("Peter", mm);
            System.out.println(dd.getMasterOrder());
            System.out.println(dd.getCompany());
        MasterOrder mmm=new MasterOrder();
            mmm.addOrder(a);
            mmm.addOrder(b);
            mmm.addOrder(c);
            mmm.addOrder(d);
            System.out.println(mm.getTotalBoxes());
            System.out.println(mmm.removeVariety("Chocolate Chip"));
        DistributionOrder ddd= new DistributionOrder("Agrim", mmm);
            System.out.println(ddd.getMasterOrder());
            System.out.println(ddd.getCompany());    
        ArrayList<CookieOrder> y= new ArrayList<CookieOrder>();
            y.add(b);
            y.add(a);
            y.add(d);
            y.add(c);
        ArrayList<DistributionOrder> ad= new ArrayList<DistributionOrder>();
           // ad.add(dd);
           // ad.add(ddd);
        WeeklyOrders ww=new WeeklyOrders(ad);
         ww.addDistribution(dd);
         ww.addDistribution(ddd);
            System.out.println(ww.toString());
            System.out.println(ww.totalNumOfBoxesOrdered("Peter"));
            System.out.println(ww.totalNumOfBoxesOrdered());
    }
}