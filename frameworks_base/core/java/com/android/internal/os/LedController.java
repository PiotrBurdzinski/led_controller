package com.android.internal.os;

import android.annotation.SystemService;
import android.content.Context;
import android.os.RemoteException;
import android.util.Slog;

/**
 * @hide
 */
@SystemService(Context.LED_CONTROLLER_SERVICE)
public class LedController {

    private static final String TAG = "LedController";

    private final ILedControllerService mService;

    public LedController(Context context, ILedControllerService service) {
        mService = service;
        if (service == null) {
            Slog.v(TAG, "ILedControllerService was null");
        }
    }

    /**
     * @hide
     */
    public void blinkRed(int times) {
        try {
            mService.blinkRed(times);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /**
     * @hide
     */
    public void blinkGreen(int times) {
        try {
            mService.blinkGreen(times);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /**
     * @hide
     */
    public void ledAlert() {
        try {
            mService.ledAlert();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
