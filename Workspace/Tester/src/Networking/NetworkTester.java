package Networking;

import java.util.ArrayList;
import org.jsoup.nodes.Element;

public class NetworkTester {

	public static final String googleApiKey = "AIzaSyBhXcNgl1iWWa68HYzW0-IxROWidJCfYGQ";
	private static ArrayList<String> productList = new ArrayList<String>();
	
	public static void main(String[] args){
		String upc = "9780805072549";
		onActivityResult(upc);
	}
	
	static void onActivityResult(String upc) {
		String category="none";
		String productTitle=null;
		if(upc.startsWith("978")||upc.startsWith("979")){
			category="books";
			if(NetworkInterfacing.searchBookTitle(upc)!=null){
				try {
					productTitle = NetworkInterfacing.searchBookTitle(upc);
				  } catch (Exception ex) {
					  ex.printStackTrace();
				  }
			}
		}
		if(productTitle==null)
			productTitle = NetworkInterfacing.searchProductTitle(upc);

		if(productTitle==null){
			System.out.println("Product not found!");
		}
		else{
			System.out.println("Product:"+productTitle+"\nKatLinks:"+getProductInfo(getProductLinks(productTitle,category),1,productTitle));
		}
	}
	
	private static ArrayList<Element> getProductLinks(String productTitle,String category){
		ArrayList<Element> katLinks = NetworkInterfacing.getKATInfo(productTitle,category);
		
		return katLinks;
	}
	
	private static String getProductInfo(ArrayList<Element> katLinks,int numLinks,String productTitle){
		if(katLinks==null)
			return "1: "+productTitle+"\nNo torrent found.\n\n";
		ArrayList<String> elementNames = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();
		
		for(Element e:katLinks){
			elementNames.add(NetworkInterfacing.getKatElementName(e));
			links.add(NetworkInterfacing.getKatElementDownloadLink(e));
		}
		String result="";
		for(int i=0;i<elementNames.size();i++){
			if(i>=numLinks)
				break;
			result+=(i+1)+": Torrent: "+elementNames.get(i)+"\nLink: "+links.get(0)+"\n\n";
		}
		return result;
	}
}