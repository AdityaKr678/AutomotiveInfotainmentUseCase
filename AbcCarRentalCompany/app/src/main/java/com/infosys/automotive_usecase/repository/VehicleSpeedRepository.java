//Copyright (C) 2024 Aditya Kumar

package com.infosys.automotive_usecase.repository;

//Need to include required import statement
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class VehicleSpeedRepository {
	
	private static final String TAG = "VehicleSpeedRepository"; // Tag for logs

    private static final int DEFAULT_CAR_SPEED_LIMIT = 80; // Default speed limit
	
    private static VehicleSpeedRepository mVehicleSpeedInstance;

    private MutableLiveData<VehicleSpeedInfo> mVehicleSpeedLiveData = new MutableLiveData<>(); // Livedata for vehicle speed info


    public static VehicleSpeedRepository getInstance() {
        if (mVehicleSpeedInstance == null) {
            mVehicleSpeedInstance = new VehicleSpeedRepository();
        }
        return mVehicleSpeedInstance;
    }

    // Method to fetch speed from vHAL
    public LiveData<VehicleSpeedInfo> getVehicleSpeed(String customerId, int speedLimit) {

		int currentVehicleSpeed = 0;         // Read current speed from vHAL. Need to register callbacks to get speed
		
        VehicleSpeedInfo speedData = new VehicleSpeedInfo(currentVehicleSpeed, speedLimit, customerId);
        mVehicleSpeedLiveData.setValue(speedData);
        return mVehicleSpeedLiveData;
    }

    public void sendAlertToCompany(String customerId, int currentSpeed) {
       Log.d(TAG, customerId + " has exceeded the speed limit. Current speed: " + currentSpeed);
    }
	
	public void sendAlertToDriver(String customerId, int currentSpeed) {
       Log.d(TAG, "Warning: You have exceeded the speed limit. Drive slow. Current speed: " + currentSpeed);
    }
}
