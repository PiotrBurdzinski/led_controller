package com.android.internal.os;

/** @hide */
interface ILedControllerService {
    void blinkRed(int times);
    void blinkGreen(int times);
    void ledAlert();
}