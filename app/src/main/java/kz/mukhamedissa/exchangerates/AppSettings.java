package kz.mukhamedissa.exchangerates;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import kz.mukhamedissa.exchangerates.util.Currency;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class AppSettings {

    private static final String APP_SETTINGS = "app_settings";

    private static SharedPreferences sSharedPreferences;
    private static final Object sMutex = new Object();

    public static final String BASE_CURRENCY = "base_currency";
    public static final String SELECTED_CURRENCIES = "selected_currencies";
    public static final String DEFAULT_CURRENCY = "KZT";
    public static final String DEFAULT_SELECTED_CURRENCIES = new Gson().toJson(Currency.getCurrencyCheckList());
    public static final String FIRST_RUN = "first_run";

    private AppSettings() {
        throw new IllegalStateException("Class " + AppSettings.class.getSimpleName() + " cannot be instantiated");
    }

    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static boolean isInstantiated() {
        return sSharedPreferences != null;
    }

    public static String getString(String key, String defValue) {
        synchronized (sMutex) {
            return sSharedPreferences.getString(key, defValue);
        }
    }

    public static void putString(String key, String value) {
        synchronized (sMutex) {
            sSharedPreferences.edit().putString(key, value).apply();
        }
    }

    public static boolean getBoolean(String key, boolean defValue) {
        synchronized (sMutex) {
            return sSharedPreferences.getBoolean(key, defValue);
        }
    }

    public static void putBoolean(String key, boolean value) {
        synchronized (sMutex) {
            sSharedPreferences.edit().putBoolean(key, value).apply();
        }
    }

}
