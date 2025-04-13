package com.example.kioskapp;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

public class KioskApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Initialize device policy manager
        DevicePolicyManager devicePolicyManager = 
            (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminComponent = new ComponentName(this, AdminReceiver.class);
        
        // Enable Kiosk mode if device owner
        if (devicePolicyManager.isDeviceOwnerApp(getPackageName())) {
            devicePolicyManager.setLockTaskPackages(adminComponent, 
                new String[]{getPackageName()});
        }
    }
}
