import com.android.server.lights.LedControllerService;

t.traceBegin("StartLedControllerService");
mSystemServiceManager.startService(LedControllerService.class);
t.traceEnd();