//Copyright (C) 2024 Aditya Kumar

package com.infosys.automotive_usecase.service;

//Need to include required import statement
import android.app.Service;
import android.content.Intent;
import android.os.Handler;


public class CarSpeedMoniterService extends Service {

    private static final String TAG = "CarSpeedMoniterService"; // Tag for logs
    private static final long CHECK_INTERVAL = 3000; // Check every 3 seconds
    private static final int DEFAULT_CAR_SPEED_LIMIT = 80; // Default speed limit

	
    private String mRentalCustomerId = " ";
	private int mVehicleSpeedLimit;
	
	private Timer mSpeedTimer;
    private Handler mSpeedCheckHandler = new Handler();
	
	private VehicleSpeedRepository mVehicleSpeedRepository;
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		mRentalCustomerId = intent.getStringExtra("customerId");
        mVehicleSpeedLimit = intent.getIntExtra("speedLimit", DEFAULT_CAR_SPEED_LIMIT);
		return START_STICKY;
	}
	
	@Override
    public void onCreate() {
        super.onCreate();

        mSpeedTimer = new Timer();
		mVehicleSpeedRepository = VehicleSpeedRepository.getInstance();
		createNotificationChannel();
        startForegroundServiceNotification();
        startSpeedCheckTask();
    }
	
	private void startSpeedCheckTask() {
        mSpeedTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    LiveData<VehicleSpeedInfo> liveSpeedData = mVehicleSpeedRepository.getSpeedData(mRentalCustomerId, mVehicleSpeedLimit);
                    VehicleSpeedInfo speedData = liveSpeedData.getValue();

                    if (speedData != null && speedData.getCurrentSpeed() > speedData.getSpeedLimit()) {
                        mVehicleSpeedRepository.sendAlertToCompany(mRentalCustomerId, speedData.getCurrentSpeed());
                        mVehicleSpeedRepository.sendAlertToDriver(mRentalCustomerId, speedData.getCurrentSpeed());
                        updateNotification("Speed limit exceeded!");
                    }
                });
            }
        }, 0, CHECK_INTERVAL);
    }
	
	private void startForegroundServiceNotification() {
        Notification notification = createNotification("Monitoring speed...");
        startForeground(1, notification);
    }

    private Notification createNotification(String contentText) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Speed Monitor")
                .setContentText(contentText)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentIntent(pendingIntent)
                .build();
    }
	
	    private void updateNotification(String message) {
        Notification notification = createNotification(message);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Speed Monitor Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
		
	@Override
    public void onDestroy() {
        super.onDestroy();
        if (mSpeedTimer != null) {
            mSpeedTimer.cancel();
        }
    }
	
	@Override
    public IBinder onBind(Intent intent) {
        return null;
    }
	
}
