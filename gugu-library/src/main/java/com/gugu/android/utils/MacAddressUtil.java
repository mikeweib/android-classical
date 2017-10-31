package com.gugu.android.utils;

import android.content.Context;
import android.os.Build;

public abstract class MacAddressUtil {

    private static MacAddressUtil IMPL;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            IMPL = new MacAddress_V24();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            IMPL = new MacAddress_V19();
        } else {
            IMPL = new MacAddress_V4();
        }
    }

    public static String getMacAddress(Context context) {
        return IMPL.getMac(context);
    }

    abstract String getMac(Context context);

}
