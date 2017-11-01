package com.gugu.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetUtil {

    public static String getNetworkType(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return "NOT_NETWORK";
        }

        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network == null || !network.isConnected()) {
            return "NOT_NETWORK";
        } else if (network.getType() == 1) {
            return "WIFI";
        } else if (network.getSubtype() >= 13) {
            return "4G";
        } else if ((network.getSubtype() >= 5) && (network.getSubtype() < 13)) {
            return "3G";
        }

        return "other";
    }

    public static boolean isConnected(final Context context) {
        return !getNetworkType(context).equals("NOT_NETWORK");
    }

}
