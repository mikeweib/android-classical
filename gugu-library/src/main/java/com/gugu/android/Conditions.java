package com.gugu.android;

import android.text.TextUtils;

public final class Conditions {

    private Conditions() {

    }

    public static <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, final String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    public static <T extends CharSequence> T checkNotEmpty(final T reference) {
        if (TextUtils.isEmpty(reference)) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    public static <T extends CharSequence> T checkNotEmpty(final T reference, final String errorMessage) {
        if (TextUtils.isEmpty(reference)) {
            throw new IllegalArgumentException(errorMessage);
        }
        return reference;
    }
}
