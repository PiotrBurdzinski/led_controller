package com.android.server.lights;

import android.content.Context;
import android.os.Binder;
import android.util.Slog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.android.server.SystemService;

import com.android.internal.os.ILedControllerService;

public class LedControllerService extends SystemService {

    private static final String TAG = "LedControllerService";

    private final Object mLock = new Object();

    private final ILedControllerService.Stub mService = new ILedControllerService.Stub() {
        public void blinkLed(String path, int times) {
            synchronized (mLock) {
                final long ident = Binder.clearCallingIdentity();
                try {
                    try (FileWriter writer = new FileWriter(path)){
                        writer.write(Integer.toString(times));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }

        public void blinkRed(int times) {
            synchronized (mLock) {
                final long ident = Binder.clearCallingIdentity();
                try {
                    blinkLed("/sys/led_controller/red_blinks", times);
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }

        public void blinkGreen(int times) {
            synchronized (mLock) {
                final long ident = Binder.clearCallingIdentity();
                try {
                    blinkLed("/sys/led_controller/green_blinks", times);
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }

        public void ledAlert() {
            synchronized (mLock) {
                final long ident = Binder.clearCallingIdentity();
                try {
                    blinkLed("/sys/led_controller/led_alert", 1);
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }
        
    };

    public LedControllerService(Context context) {
        super(context);
    }

    @Override
    public void onStart(){
        publishBinderService(Context.LED_CONTROLLER_SERVICE, mService);
    }

}