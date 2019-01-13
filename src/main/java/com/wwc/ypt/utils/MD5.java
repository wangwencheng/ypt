package com.wwc.ypt.utils;

/**
 * @author 王文城 wangwencheng
 * @Title:TODO 类描述
 * @Description:TODO 详细描述
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018/7/2 14:09
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public final static String toMD5(String s) {
        byte cResult[] = new byte[16];
        String sResult = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(("terton" + s).getBytes());
            cResult = md.digest();
            for (int i = 0; i < cResult.length; i++) {
                if (cResult[i] < 0) {
                    cResult[i] += 128;
                }
                String sTemp = Integer.toHexString(cResult[i]).toUpperCase();
                if (cResult[i] < 16) {
                    sTemp = "0" + sTemp;
                }
                sResult += sTemp;
            }
        } catch (Exception e) {
            sResult = "";
        }
        return sResult;
    }

    public static final String toMD5(byte[] message) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(message);
            byte[] abyte = digest.digest();
            return byte2hex(abyte);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private static String byte2hex(byte[] b) {
        String hs = "", stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else
                hs = hs + stmp;
        }
        return hs.toLowerCase();
    }

	/*public static final void main(String[] args) {
		System.out.println("123456 = " + MD5.toMD5("123456"));
	}*/
    public static String Md5_32(String plainText) {
        StringBuffer buf = new StringBuffer("");

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
        }

        return buf.toString().toUpperCase();
    }
}