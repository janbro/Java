import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Anakin {
	public static void main(String[] args) throws NumberFormatException, IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int row = Integer.parseInt((br.readLine()));
		int rac;
		for(int r=0; r<row; r++){
			HashMap<String, String> hm = new HashMap<String,String>();
			ArrayList<String> inOrder = new ArrayList<String>();
			ArrayList<String> names = new ArrayList<String>();
			rac=Integer.parseInt((br.readLine()));
			String name=null;
			for(int num=0;num<rac;num++){
				name = br.readLine();
				names.add(name);
				hm.put(name, br.readLine());
			}//System.out.println(names.toString());
			for(int i=0;i<names.size();i++){
				if(inOrder.size()==0){
					inOrder.add(names.get(i));
				}
				else{
					boolean added=false;
					for(int j=0;j<inOrder.size();j++){
						
						if(Double.parseDouble(hm.get(names.get(i)).toString())>=Double.parseDouble((String) hm.get(inOrder.get(j)))){
							if(Double.parseDouble(hm.get(names.get(i)).toString())==Double.parseDouble((String) hm.get(inOrder.get(j))))
								{//System.out.println("GGWT");for(j=j;j<inOrder.size();j++)
									if(names.get(i).compareTo(inOrder.get(j))>0)
									{break;
									}
								}
							inOrder.add(j,names.get(i));
							added=true;
							break;
						}
					}if(!added){
						inOrder.add(names.get(i));
					}
				}
			}
			System.out.println("Case "+(r+1)+":"+inOrder.toString().replace("[", " ").replace("]", ""));
		}
	}
}
