package tools.mango.cordova.plugin.android;


import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.mango.cordova.plugin.android.IpUtils;



public class IpAddress extends CordovaPlugin {

    JSONObject netConfig = new JSONObject();

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        WifiManager wifiMgr = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo ipConfig   = wifiMgr.getDhcpInfo();

        if(IpUtils.toIpv4(ipConfig.ipAddress).compareTo("0.0.0.0")!= 0){
            netConfig.put("ipAddress",IpUtils.toIpv4(ipConfig.ipAddress));
        }
        netConfig.put("ipAddress",IpUtils.toIpv4(ipConfig.ipAddress));
        netConfig.put("macAddress", wifiMgr.getConnectionInfo().getMacAddress());
        netConfig.put("netmask", IpUtils.toIpv4(ipConfig.netmask));
        netConfig.put("gateway", IpUtils.toIpv4(ipConfig.gateway));
        netConfig.put("dns1",IpUtils.toIpv4(ipConfig.dns1));
        netConfig.put("dns2",IpUtils.toIpv4(ipConfig.dns1));

        if (netConfig != null) {
            callbackContext.success(netConfig);
            return true;
        } else {
            callbackContext.error("Operation failed");

            return false;
        }
    }

}

