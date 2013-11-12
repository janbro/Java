package com.example.torrentscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.simple.*;
import org.jsoup.nodes.Element;
import java.util.ArrayList;

public class NetworkInterfacing {
	private static final String googleApiKey = "AIzaSyBhXcNgl1iWWa68HYzW0-IxROWidJCfYGQ";
	

	public static String searchProductTitle(String upc){
		String prodTitle=null;
		JSONObject prodJSON = null;
		
		try {
			prodJSON = getShopJSON(upc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(prodJSON!=null){
			try{
				prodTitle = (String) ((JSONObject)((JSONObject)((JSONArray)prodJSON.get("items")).get(0)).get("product")).get("title");
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		return prodTitle;
	}
	
	public static String searchBookTitle(String isbn){
		String bookTitle="";
		String subTitle=null;
		
		JSONObject bookJSON = null;
		try {
			bookJSON = getBookJSON(isbn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bookJSON!=null)
			bookTitle = (String) ((JSONObject)((JSONObject)((JSONArray)bookJSON.get("items")).get(0)).get("volumeInfo")).get("title");
		try{
			subTitle = (String) ((JSONObject)((JSONObject)((JSONArray)bookJSON.get("items")).get(0)).get("volumeInfo")).get("subtitle");
		}
		finally{
			
		}if(subTitle!=null)
			bookTitle+=": "+subTitle;
		
		return bookTitle;
	}
	
	public static ArrayList<Element> getKATInfo(String search,String category){
		Document doc = null;
		try {
			doc = getKATHTML(search,category);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(doc==null)
			return null;
		ArrayList<Element> downloads = new ArrayList<Element>();
		for(Element e:doc.getElementsByTag("tr")){
			if(e.hasClass("odd")||e.hasClass("even"))
				downloads.add(e);
		}
		return downloads;
	}
	
	public static String getKatElementDownloadLink(Element e){
		if(e==null)
			return null;
		return e.getElementsByAttributeValueContaining("title", "torrent file").attr("href");
	}
		
	public static String getKatElementName(Element e){
		if(e==null)
			return null;
		return e.getElementsByClass("torrentname").get(0).child(1).text();
	}
	
	private static JSONObject getShopJSON(String barcode) throws IOException{
		String url = "https://www.googleapis.com/shopping/search/v1/public/products?key="+googleApiKey+"&country=US&q="+barcode+"&alt=json";
		
		URL requestURL=null;
		try {
			requestURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = requestURL.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String input;
		String result="";
		
		while((input = in.readLine()) != null)
			result += input;
		
		in.close();

		return (JSONObject)JSONValue.parse(result);
	}
	
	private static JSONObject getBookJSON(String barcode) throws IOException{
		String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+barcode+"&key="+googleApiKey;
		
		URL requestURL = new URL(url);
		URLConnection connection = requestURL.openConnection();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String input;
		String result="";
		
		while((input = in.readLine()) != null)
			result += input;
		
		in.close();

		return (JSONObject)JSONValue.parse(result);
	}
	
	private static Document getKATHTML(String search, String category) throws IOException{
		String requestUrl = "http://kickass.to/usearch/"+URLEncoder.encode(search,"UTF-8")+"%20category%3A"+category+"/?field=seeders&sorder=desc";
		URL katRequest = new URL(requestUrl);

		Document doc = Jsoup.parse(katRequest, 5000);

		return doc;
	}
}
