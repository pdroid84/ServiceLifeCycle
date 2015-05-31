package com.example.servicelifecycle;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ExampleService extends Service {
    int mStartMode;       // indicates how to behave if the service is killed
    IBinder mBinder = new LocalBinder();      // interface for clients that bind
    boolean mAllowRebind; // indicates whether onRebind should be used
    private final Random mGenerator = new Random();
    
    public class LocalBinder extends Binder {
    	ExampleService getService(){
    		Log.d("DEB","getService of LocalBinder is called");
    		return ExampleService.this;
    	}
    }

    @Override
    public void onCreate() {
        // The service is being created
    	Log.d("DEB", "Service - onCreate is called");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
    	Log.d("DEB", "Service - onStartCommand is called");
    	Toast.makeText(this, "Service is being created", Toast.LENGTH_LONG).show();
        return mStartMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
    	Log.d("DEB", "Service - onBind is called");
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
    	Log.d("DEB", "Service - onUnbind is called");
        return mAllowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    	Log.d("DEB", "Service - onRebind is called");
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
    	Log.d("DEB", "Service - onDestroy is called");
    }
    public int getRandom(){
    	return mGenerator.nextInt(100);
    }
}
