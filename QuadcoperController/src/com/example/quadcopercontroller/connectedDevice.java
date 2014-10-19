package com.example.quadcopercontroller;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;



public class connectedDevice extends Activity implements CompoundButton.OnCheckedChangeListener {
	
	private SeekBar _joyX = null;
	private SeekBar _joyY = null;
	private SeekBar _yaw = null;
	private SeekBar _throttle = null;
	private Switch _switch = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communicate);
		
		_joyX = (SeekBar) findViewById(R.id.seekBar1);
		_joyY = (SeekBar) findViewById(R.id.seekBar2);
		_yaw = (SeekBar) findViewById(R.id.seekBar3);
		_throttle = (SeekBar) findViewById(R.id.seekBar4);
		_switch = (Switch) findViewById(R.id.switch1);
		
		_joyX.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progressChanged = 0;
 
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
				progressChanged = progress;
			}
 
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
 
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(connectedDevice.this,"seek bar progress:"+progressChanged, 
						Toast.LENGTH_SHORT).show();
			}
		});
		
		if(_switch != null){
			_switch.setOnCheckedChangeListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		        	   finish();
		           }
		       });
		builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		           }
		       });
		// Set other dialog properties
		builder.setMessage("Are you sure you want to exit?");

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}		
	
	@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       Toast.makeText(this, "Monitored switch is " + (isChecked ? "on" : "off"),
               Toast.LENGTH_SHORT).show();
    }

}
