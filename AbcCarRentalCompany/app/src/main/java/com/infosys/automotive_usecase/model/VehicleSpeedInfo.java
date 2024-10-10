//Copyright (C) 2024 Aditya Kumar

public class VehicleSpeedInfo {
    private int mCurrentSpeed; // for current speed of the vehicle
    private int mSpeedLimit; // Speed limit which will be set by rental company
    private String mCustomerId; // Customer ID for every customer

    public VehicleSpeedInfo(int currentSpeed, int speedLimit, String customerId) {
        mCurrentSpeed = currentSpeed;
        mSpeedLimit = speedLimit;
        mCustomerId = customerId;
    }

	// Get current speed of the vehicle
    public int getCurrentSpeed() { 
	return mCurrentSpeed; 
	}
	
    // Set current speed of the vehicle
    public void setCurrentSpeed(int currentSpeed) { 
	mCurrentSpeed = currentSpeed; 
	}

	// Get speed limit for the vehicle
    public int getSpeedLimit() { 
	return mSpeedLimit; 
	}
    
	// Set speed limit for the vehicle
	public void setSpeedLimit(int speedLimit) { 
	mSpeedLimit = speedLimit; 
	}

	// Get current customer id
    public String getCustomerId() { 
	return mCustomerId; 
	}
    
	// Set current customer id
	public void setCustomerId(String customerId) { 
	mCustomerId = customerId; 
	}
}
