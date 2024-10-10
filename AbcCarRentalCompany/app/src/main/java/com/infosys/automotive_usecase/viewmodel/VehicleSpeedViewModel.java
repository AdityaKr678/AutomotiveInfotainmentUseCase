//Copyright (C) 2024 Aditya Kumar

package com.infosys.automotive_usecase.viewmodel;

//Need to include required import statement
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class VehicleSpeedViewModel extends ViewModel {

    private final VehicleSpeedRepository mVehicleSpeedRepository;
	
    private LiveData<VehicleSpeedInfo> mVehicleSpeedLiveData;

    public VehicleSpeedViewModel() {
        mVehicleSpeedRepository = VehicleSpeedRepository.getInstance();
    }

    public LiveData<VehicleSpeedInfo> getSpeedData(String customerId, int speedLimit) {
        if (mVehicleSpeedLiveData == null) {
            mVehicleSpeedLiveData = mVehicleSpeedRepository.getSpeedData(customerId, speedLimit);
        }
        return mVehicleSpeedLiveData;
    }
}
