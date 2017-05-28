package kz.mukhamedissa.exchangerates.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.CurrencyCheck;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class Utils {

    private static final String ENDPOINT = "https://query.yahooapis.com/v1/public/yql?q=";
    private static final String QUERY = new StringBuilder("select * from yahoo.finance.xchange where pair in ")
            .append("(%s)")
            .append("&format=json&env=store://datatables.org/alltableswithkeys")
            .toString();

    private Utils() {
        throw new IllegalStateException("Class " + Utils.class.getSimpleName() + " cannot be instantiated");
    }

    @NonNull
    public static String getEndpointWithPairs(@NonNull String pairs) throws UnsupportedEncodingException {
        String encodedQueryWithPairs = URLEncoder.encode(String.format(QUERY, pairs), "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("%28", "(")
                .replaceAll("%29", ")")
                .replaceAll("%3D", "=")
                .replaceAll("%26", "&");

        return ENDPOINT + encodedQueryWithPairs;
    }

    @NonNull
    public static String getPairs(@NonNull List<String> rateIds) {
        String pairs = "";

        for (int i = 0; i < rateIds.size(); i++) {
            pairs += "\"" + rateIds.get(i) + "\"";
            if (i != rateIds.size() - 1) {
                pairs += ", ";
            }
        }

        return pairs;
    }

    @NonNull
    public static ArrayList<String> generateRateIds(@NonNull String baseCurrency, @NonNull String selectedCurrencies) {
        Gson gson = new Gson();
        List<CurrencyCheck> currencyCheckList
                = gson.fromJson(selectedCurrencies, new TypeToken<List<CurrencyCheck>>(){}.getType());

        ArrayList<String> rateIds = new ArrayList<>();

        for (CurrencyCheck currencyCheck: currencyCheckList) {
            if (currencyCheck.isChecked()) {
                rateIds.add(String.format("%s%s", currencyCheck.getName(), baseCurrency));
            }
        }

        return rateIds;
    }

    public static boolean isInternetAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
