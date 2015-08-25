package tools.mango.cordova.plugin.android;


import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.apache.cordova.PluginResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class IPAddress extends CordovaPlugin {
    WifiManager wifiMgr;
    DhcpInfo ipConfig;

    JSONObject netConfig = new JSONObject();

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        wifiMgr= (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
        ipConfig= wifiMgr.getDhcpInfo();

        if(IPv4.toIpv4(ipConfig.ipAddress).compareTo("0.0.0.0")!= 0){
            netConfig.put("ipAddress",IPv4.toIpv4(ipConfig.ipAddress));
        }
        netConfig.put("ipAddress",IPv4.toIpv4(ipConfig.ipAddress));
        netConfig.put("macAddress",IPv4.toIpv4(ipConfig.ipAddress));
        netConfig.put("netmask", ipConfig.netmask);
        netConfig.put("localSubnet", IPv4.toIpv4(ipConfig.netmask));
        netConfig.put("gateway", ipConfig.gateway);
        netConfig.put("dns1",ipConfig.dns1);
        netConfig.put("dns2",ipConfig.dns1);

        if (netConfig != null && netConfig.length() > 0) {
            callbackContext.success(netConfig);
            return true;
        } else {
            callbackContext.error("Operation failed");

            return false;
        }
    }

    public static String getMacAddress(Context context) {
        wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiMgr.getConnectionInfo().getMacAddress();
    }
}

