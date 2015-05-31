package com.example.servicelifecycle;

import com.example.servicelifecycle.ExampleService.LocalBinder;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;



public class MainActivity extends Activity {
	ExampleService mService;
	boolean mBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEB","Activity - onCreate is called");
    }
    public void onStart(){
    	super.onStart();
    	Log.d("DEB","Activity - onStart is called");
    	Intent intent = new Intent(this,ExampleService.class);
    	bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	Log.d("DEB","Activity - onStop is called");
    	if(mBound){
    		unbindService(mConn);
    		mBound=false;
    	}
    }
    public void startService(View v) {
    	if(mBound){
    		Log.d("DEB","Activity - startService is called");
    		int num = mService.getRandom();
    		Log.d("DEB", "The number retrieved from service="+num);
    		Toast.makeText(this, "The random num is "+num, Toast.LENGTH_LONG).show();
    	}
    }
    private ServiceConnection mConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d("DEB","Activity - onServiceDisconnected is called");
			mBound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("DEB","Activity - onServiceConnected is called.Component name="+name.toString());
			LocalBinder binder = 	(LocalBinder) service;
			mService = binder.getService();
			mBound = true;
		}
	};
}
