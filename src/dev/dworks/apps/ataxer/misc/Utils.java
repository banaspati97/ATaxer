package dev.dworks.apps.ataxer.misc;import java.math.BigDecimal;import java.text.NumberFormat;import java.util.Locale;import android.content.Context;import android.graphics.Bitmap;import android.graphics.Canvas;import android.preference.PreferenceManager;import android.text.TextUtils;import android.view.View;public class Utils {	public static final String DATA = "bundle_data";	public static final String PIN_ENABLED = "pin_enable";    public static final String KEY_PIN = "pin";	public static NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("en", "in"));	//public static DecimalFormatSymbols dfs = new DecimalFormatSymbols();	public static Bitmap drawViewOntoBitmap(View paramView) {		Bitmap localBitmap = Bitmap.createBitmap(paramView.getWidth(),				paramView.getHeight(), Bitmap.Config.RGB_565);		paramView.draw(new Canvas(localBitmap));		return localBitmap;	}	public static String getCategory(int paramInt) {		String str = "";		switch (paramInt) {		case 1:			str = "Male";			break;		case 2:			str = "Female";			break;		case 3:			str = "Sr. Citizen";			break;		case 4:			str = "Super Sr. Citizen";			break;		}		return str;	}	public static String getFinancialYr(int year) {		String str = "";		switch (year) {		case 1:			str = "2012-2013";			break;		case 2:			str = "2013-2014";			break;		case 3:			str = "2014-2015";			break;		case 4:			str = "2015-2016";			break;		}		return str;	}	public static String getFormattedString(long paramLong) {		BigDecimal localBigDecimal = new BigDecimal(paramLong);		return currencyFormat.format(localBigDecimal);	}		public static final boolean isPinEnabled(Context context) {        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PIN_ENABLED, false)        		&& isPinProtected(context);    }	    public static final boolean isPinProtected(Context context) {        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PIN, "") != "";    }        public static void setPin(Context context, String pin) {    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_PIN, hashKeyForPIN(pin)).commit();    }        public static boolean checkPin(Context context, String pin) {        pin = hashKeyForPIN(pin);        String hashed = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PIN, "");        if (TextUtils.isEmpty(pin))            return TextUtils.isEmpty(hashed);        return pin.equals(hashed);    }        private static String hashKeyForPIN(String value) {        if (TextUtils.isEmpty(value))            return null;        try {            //MessageDigest digester = MessageDigest.getInstance("MD5");            //return Base64.encodeToString(value.getBytes(), Base64.DEFAULT);        }        catch (Exception e) {        }        return value;    }        public static int getTaxSlab10(int year){		int slab = 0;		switch (year) {		case 1:			slab = 200000;			break;		case 2:			slab = 200000;			break;		case 3:			slab = 250000;			break;		case 4:			slab = 250000;			break;		}		return slab;    }        public static int getTaxSlabSenior10(int year){		int slab = 0;		switch (year) {		case 1:			slab = 250000;			break;		case 2:			slab = 250000;			break;		case 3:			slab = 300000;			break;		case 4:			slab = 300000;			break;		}		return slab;    }    /*    public static String hashKeyForPIN(String key) {        String cacheKey = key;        try {            final MessageDigest mDigest = MessageDigest.getInstance("MD5");            mDigest.update(key.getBytes());            cacheKey = bytesToHexString(mDigest.digest());        } catch (NoSuchAlgorithmException e) {            cacheKey = String.valueOf(key.hashCode());        }        cacheKey = Base64.encodeToString(key.getBytes(), Base64.DEFAULT);        return cacheKey;    }*/}