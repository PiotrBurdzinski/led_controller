import com.android.internal.os.ILedControllerService;
import com.android.internal.os.LedController;

registerService(Context.LED_CONTROLLER_SERVICE, LedController.class,
        new CachedServiceFetcher<LedController>() {
    @Override
    public LedController createService(ContextImpl ctx) {
        IBinder binder = ServiceManager.getService(Context.LED_CONTROLLER_SERVICE);
        ILedControllerService service = ILedControllerService.Stub.asInterface(binder);
        return new LedController(ctx.getOuterContext(), service);
    }});
