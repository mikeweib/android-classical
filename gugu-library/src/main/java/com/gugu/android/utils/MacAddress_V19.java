package com.gugu.android.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

final class MacAddress_V19 extends MacAddressUtil {

    String getMac(Context context) {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return macSerial;
    }
}
