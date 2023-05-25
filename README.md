# Led controller system service
## Implementation
The code is separated into folders (frameworks_base and system_sepolicy) as those are the 2 directories needed for the service to work. For faster and easier implementation I've also made the second branch "implex" that shows where the code belongs for files that aren't created from scratch. It's **crucial** for the service to have proper sepolicy as it might cause the device to bootloop or crash. 

## Usage
When it comes to usage it's very easy:

    import  com.android.internal.os.ILedControllerService;
    import  com.android.internal.os.LedController;

    LedController  mLedController  =  getSystemService(LedController.class);
    mLedController.blinkRed(3);

 *(examples in Java)*

There are 3 functions as we talked before:

    blinkRed(int times) - blinks red led *times* amount of times
    blinkGreen(int times) - blinks green led *times* amount of times
    ledAlert() - blinks the leds in following order once: red-green-red-green-off


## Sepolicy
I've tried to put all the sepolicy rules in the system sepolicy to skip the device dependecy in case the device changes.
It's very important to make sure there aren't any problems with sepolicy as it can cause the device to bootloop or the service won't work as intended. The only part left device specific is in the init file to make sure the directory is owned by system instead of root.
Also the kernel module was edited to work on /sys/ instead of /proc/ directories.

## Example implementation
The implementation provided on *implex* branch is done on Android 13 AOSP system, therefore it should be the same or at least very similar to your target system. What's worth noting is the good practise to test of service was added proprely with selinux set to permissive. To do that add the below code to BoardConfig.mk in target device:

    BOARD_KERNEL_CMDLINE += androidboot.selinux=permissive

With that in mind even if something goes wrong the service won't crash and would just log the denial which can help with debugging and fixing the issue.
In some cases there might be an error about neverallows, to temporary disable it add the following code to BoardConfig.mk of targeted device:

    SELINUX_IGNORE_NEVERALLOWS := true

For that temporary workaround userdebug or eng build is needed.
This isn't ideal so if it happens, we will work around it.