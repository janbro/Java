package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class NetworkTester {

	public static final String googleApiKey = "AIzaSyBhXcNgl1iWWa68HYzW0-IxROWidJCfYGQ";
	private static ArrayList<String> productList = new ArrayList<String>();
	
	public static void main(String[] args){
		try {
			String upc="9780446583145";
			String url2 = "http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/json/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword="+upc;
			String result = downloadUrl(url2);
			//System.out.println(result);
			String res2=result;
	        for(int i=0;i<4;i++)
	        	res2=res2.substring(res2.indexOf("name")+7);
	        res2=res2.substring(0,res2.indexOf("\",\""));
	        System.out.println(res2);
	        if(res2.contains("keyword"))
	        	res2=res2.substring(0,res2.indexOf(url2.substring(url2.indexOf("keyword=")+8)));
			try{
				String category = "none";
				if(upc.startsWith("978")||upc.startsWith("979"))
					category="books";
				String url = "http://kickass.to/usearch/"+URLEncoder.encode(res2,"UTF-8").replace(" ", "%20")+"%20category%3A"+category+"/?field=seeders&sorder=desc";
				//String html = Jsoup.connect(url).get().html();
				//System.out.println(res2);
				String res=Jsoup.parse(new URL(url), 5000).toString();
				//System.out.println(res);
    	        String download=res.substring(res.indexOf("Download torrent file")+29);
				res=res.substring(res.indexOf("torrentname"));
				res=res.substring(res.indexOf("href")+7);
    			String seeds = download.substring(download.indexOf("green")+14);
    			seeds = seeds.substring(0,seeds.indexOf("</td>"));
    	        download = download.substring(0,download.indexOf("\""));
				System.out.println(res.substring(0,res.indexOf("\""))+"\n"+download+"\n"+seeds);
			}catch(IOException e){
				System.out.println("No torrent found!");
			}catch(IndexOutOfBoundsException e){
				System.out.println("Bad stuff");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			System.out.println(NetworkInterfacing.getBookJSON("9781620401392"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//getProductInfo(NetworkInterfacing.getKATInfo("Language of Mathematica","book"),3,"Language of Mathematica"));

		String upc = "9781620401392";
		onActivityResult(upc);*/
	}
	
	public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "utf-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
	
	private static String downloadUrl(String myurl) throws IOException {
	    InputStream is = null;
	    // Only display the first 500 characters of the retrieved
	    // web page content.
	    int len = 99999999;
	        
	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        String contentAsString = readIt(is, len);
	        return contentAsString;
	        
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
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
		ArrayList<Element> katLinks=null;
		try{
			katLinks = NetworkInterfacing.getKATInfo(productTitle,category);
		}catch(Exception e){
			return null;
		}
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
