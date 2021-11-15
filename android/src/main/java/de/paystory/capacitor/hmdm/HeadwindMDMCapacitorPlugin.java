package de.paystory.capacitor.hmdm;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.hmdm.HeadwindMDM;
import com.hmdm.MDMService;

@CapacitorPlugin(name = "HeadwindMDM")
public class HeadwindMDMCapacitorPlugin extends Plugin implements HeadwindMDM.EventHandler {
    private HeadwindMDM headwindMDM;

    @Override
    public void load() {
        headwindMDM = HeadwindMDM.getInstance();
        ensureMDMIsConnected();
    }

    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        ensureMDMIsConnected();
    }

    @Override
    protected void handleOnDestroy() {
        headwindMDM.disconnect(bridge.getContext());
        super.handleOnDestroy();
    }

    @PluginMethod
    public void connect(PluginCall call) {
        try {
            call.resolve(new JSObject().put("connected", headwindMDM.connect(bridge.getContext(), this)));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void disconnect(PluginCall call) {
        try {
            headwindMDM.disconnect(bridge.getContext());
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void isConnected(PluginCall call) {
        try {
            call.resolve(new JSObject().put("isConnected", headwindMDM.isConnected()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getServerHost(PluginCall call) {
        try {
            call.resolve(new JSObject().put("serverHost", headwindMDM.getServerHost()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getSecondaryServerHost(PluginCall call) {
        try {
            call.resolve(new JSObject().put("secondaryServerHost", headwindMDM.getSecondaryServerHost()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getServerPath(PluginCall call) {
        try {
            call.resolve(new JSObject().put("serverPath", headwindMDM.getServerPath()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getServerUrl(PluginCall call) {
        try {
            call.resolve(new JSObject().put("serverUrl", headwindMDM.getServerUrl()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getSecondaryServerUrl(PluginCall call) {
        try {
            call.resolve(new JSObject().put("secondaryServerUrl", headwindMDM.getSecondaryServerUrl()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getDeviceId(PluginCall call) {
        try {
            call.resolve(new JSObject().put("deviceId", headwindMDM.getDeviceId()));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getCustom(PluginCall call) {
        if (!ensureDataIsSet(call, "number")) { return; }

        try {
            call.resolve(new JSObject().put("custom", headwindMDM.getCustom(call.getInt("number"))));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void log(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "level")) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.log(call.getInt("level"), call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void v(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.v(call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void d(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.d(call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void i(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.i(call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void w(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.w(call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void e(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "tag")) { return; }
        if (!ensureDataIsSet(call, "message")) { return; }

        try {
            MDMService.Log.e(call.getString("tag"), call.getString("message"));
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void get(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "attr")) { return; }

        try {
            call.resolve(new JSObject().put("value", MDMService.Preferences.get(call.getString("attr"), call.getString("defValue", ""))));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void set(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }
        if (!ensureDataIsSet(call, "attr")) { return; }
        if (!ensureDataIsSet(call, "value")) { return; }

        try {
            call.resolve(new JSObject().put("successful", MDMService.Preferences.set(call.getString("attr"), call.getString("value"))));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void apply(PluginCall call) {
        if (!ensureMDMIsConnectedCallback(call)) { return; }

        MDMService.Preferences.apply();
        call.resolve();
    }

    @Override
    public void onHeadwindMDMConnected() {
        notifyListeners("onHeadwindMDMConnected", new JSObject());
    }

    @Override
    public void onHeadwindMDMDisconnected() {
        notifyListeners("onHeadwindMDMDisconnected", new JSObject());
    }

    @Override
    public void onHeadwindMDMConfigChanged() {
        notifyListeners("onHeadwindMDMConfigChanged", new JSObject());
    }

    private boolean ensureMDMIsConnectedCallback(PluginCall call) {
        if (!this.ensureMDMIsConnected()) {
            call.reject("Device is not connected!");
            return false;
        }
        return true;
    }

    private boolean ensureDataIsSet(PluginCall call, String parameter) {
        if (!call.getData().has(parameter)) {
            call.reject("Please specify parameter: " + parameter);
            return false;
        }
        return true;
    }

    private boolean ensureMDMIsConnected() {
        if (!headwindMDM.isConnected()) {
            return headwindMDM.connect(bridge.getContext(), this);
        }
        return true;
    }
}
