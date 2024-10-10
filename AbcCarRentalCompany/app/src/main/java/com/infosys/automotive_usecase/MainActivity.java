//Copyright (C) 2024 Aditya Kumar

package com.infosys.automotive_usecase;

//Need to include required import statement
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.infosys.automotive_usecase.notification.Notification;
import com.infosys.automotive_usecase.viewmodel.VehicleSpeedViewModel;

public class MainActivity extends AppCompatActivity {
	
	private Intent mServiceIntent;
	private VehicleSpeedViewModel mVehicleSpeedViewModel;
	private String mCustomerName;
	private int mVehicleSpeedLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		// Get customr name or ID from the rental company
		mCustomerName = "Aditya_Kumar";
		mVehicleSpeedLimit = 80;
		
		mVehicleSpeedViewModel = new ViewModelProvider(this).get(VehicleSpeedViewModel.class);
        observeVehicleSpeedData();

		// Start CarSpeedMoniterService
        mServiceIntent = new Intent(this, CarSpeedMoniterService.class);
		mServiceIntent.putExtra("customerId", mCustomerName);
        mServiceIntent.putExtra("speedLimit", mVehicleSpeedLimit);
        startService(intent);
		
    }
	
	private void observeVehicleSpeedData() {
        mVehicleSpeedViewModel.getSpeedData(mCustomerName, mVehicleSpeedLimit).observe(this, new Observer<VehicleSpeedInfo>() {
            @Override
            public void onChanged(VehicleSpeedInfo vehicleSpeedData) {
                if (vehicleSpeedData != null && vehicleSpeedData.getCurrentSpeed() > vehicleSpeedData.getSpeedLimit()) {
                    Notification.showSpeedLimitExceededNotification(MainActivity.this,
                            "Speed limit exceeded! Current speed: " + vehicleSpeedData.getCurrentSpeed());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop CarSpeedMoniterService
		if (mServiceIntent != null) {
            stopService(mServiceIntent);
        }
    }
}
