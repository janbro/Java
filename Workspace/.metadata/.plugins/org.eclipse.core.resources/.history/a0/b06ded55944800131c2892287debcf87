package com.scanner.torrentscanner;

import java.util.ArrayList;
import org.jsoup.nodes.Element;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BarcodeScanner extends Activity {

	public static final String googleApiKey = "AIzaSyBhXcNgl1iWWa68HYzW0-IxROWidJCfYGQ";
	final Context context = this;
	private static ArrayList<String> productList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode_scanner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.barcode_scanner, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_scan:
	    		IntentIntegrator.initiateScan(this);
	            return true;
	        case R.id.action_clear:
	            productList.clear();
	            showText("");
	            return true;
	        case R.id.action_exit:
	        	productList.clear();
	        	finish();
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case IntentIntegrator.REQUEST_CODE: {
				if (resultCode != RESULT_CANCELED) {
					IntentResult scanResult;
					try{
						scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
					}finally{}
					if (scanResult != null) {
						String category="none";
						String upc = scanResult.getContents();
						String productTitle=null;
						//Debugging Purposes
						//isbn = "9780805072549";
						//upc = "710425491016";
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
							Toast toast = Toast.makeText(context, "Product not found!", Toast.LENGTH_SHORT);
							toast.show();
						}
						else{
							Log.d("DEBUG",productTitle);
							Log.d("DEBUG", upc);
							if(productTitle.contains(upc)){
								Log.d("DEBUG","Inside");
								productTitle = productTitle.replace(upc, "");
							}
							verifyProduct(productTitle,category);
						}
					}else{
						Toast toast = Toast.makeText(context, "Could not open camera!", Toast.LENGTH_SHORT);
						toast.show();
					}
				}
			break;		
			}
		}
	}
	
	private ArrayList<Element> getProductLinks(String productTitle,String category){
		ArrayList<Element> katLinks = NetworkInterfacing.getKATInfo(productTitle,category);
		
		return katLinks;
	}
	
	private String getProductInfo(ArrayList<Element> katLinks,int numLinks,String productTitle){
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
	private void showText(String result){
		//put whatever you want to do with the code here
		TextView tv = new TextView(this);
		tv.setLinksClickable(true);
		tv.setText(result);
		Linkify.addLinks(tv, Linkify.ALL);
		setContentView(tv);
	}
	
	private void verifyProduct(final String productTitle,final String category){
		String products="";
		for(String str:productList)
			products+=str+"\n";
		products+=productTitle;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
 
			// set title
			alertDialogBuilder.setTitle("Verify Product");
 
			// set dialog message
			alertDialogBuilder
				.setMessage(products)
				.setCancelable(false)
				.setPositiveButton("Done",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						ProgressDialog progress = new ProgressDialog(context);
						progress.setTitle("Loading");
						progress.setMessage("Wait while loading...");
						progress.show();
						productList.add(productTitle);
						// if this button is clicked, close
						// current activity
						dialog.cancel();
						String finalText="";
						if(productList.size()>1)
							for(String prod:productList){
								finalText+=getProductInfo(getProductLinks(prod,category),1,prod);
							}
						else{
							ArrayList<Element> downloadLinks = getProductLinks(productTitle,category);
							if(downloadLinks!=null)
								finalText=getProductInfo(downloadLinks,downloadLinks.size(),productTitle);
							else{
								finalText=productTitle+"\nNo torrent found!";
								Toast toast = Toast.makeText(context, "No torrent not found!", Toast.LENGTH_LONG);
								toast.show();
								productList.clear();
							}
						}
						progress.dismiss();
						showText(finalText);
					}
				  })
				.setNeutralButton("Next Scan", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						productList.add(productTitle);
						// TODO Auto-generated method stub
						dialog.cancel();
						IntentIntegrator.initiateScan(BarcodeScanner.this);
					}
						
				})
				  .setNegativeButton("Restart Scan",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						productList.clear();
						IntentIntegrator.initiateScan(BarcodeScanner.this);
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
	}
}
