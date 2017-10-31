package com.gugu.android.utils;

public final class HexUtil {

    private static String digits = "0123456789ABCDEF";

    /**
     * Return length many bytes of the passed in byte array as a hex string.
     *
     * @param data   the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i != length; i++) {
            int v = data[i] & 0xff;

            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }

        return buf.toString();
    }

    /**
     * Return the passed in byte array as a hex string.
     *
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data) {
        return toHex(data, data.length);
    }

    public static byte[] getByteArray(String hexString) {
        byte[] hexbyte = hexString.getBytes();
        byte[] bitmap = new byte[hexbyte.length / 2];
        for (int i = 0; i < bitmap.length; i++) {
            hexbyte[i * 2] -= hexbyte[i * 2] > '9' ? 7 : 0;
            hexbyte[i * 2 + 1] -= hexbyte[i * 2 + 1] > '9' ? 7 : 0;
            bitmap[i] = (byte) ((hexbyte[i * 2] << 4 & 0xf0) | (hexbyte[i * 2 + 1] & 0x0f));
        }
        return bitmap;
    }
}
