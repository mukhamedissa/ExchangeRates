package kz.mukhamedissa.exchangerates.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;
import kz.mukhamedissa.exchangerates.data.model.JSONResponse;
import kz.mukhamedissa.exchangerates.util.Utils;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class YahooCurrencyAPIClient {

    private static final String TAG = YahooCurrencyAPIClient.class.getSimpleName();

    private YahooCurrencyAPIClient() {
        throw new IllegalStateException("Class" + YahooCurrencyAPIClient.class.getSimpleName()
                + " cannot be instantiated");
    }

    @Nullable
    public static List<ExchangeRate> getExchangeRates(@NonNull List<String> rateIds) throws UnsupportedEncodingException {
        if (rateIds.isEmpty()) {
            return null;
        }

        String pairs = Utils.getPairs(rateIds);

        String jsonResponse = HttpHandler.makeServiceCall(Utils.getEndpointWithPairs(pairs));

        Gson gson = new Gson();
        JSONResponse response = gson.fromJson(jsonResponse, JSONResponse.class);

        if (response == null) {
            return null;
        }

        return response.getQuery().getResult().getRate();
    }
}
