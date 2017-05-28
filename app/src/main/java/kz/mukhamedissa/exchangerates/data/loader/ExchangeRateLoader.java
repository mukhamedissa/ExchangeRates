package kz.mukhamedissa.exchangerates.data.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.CurrencyCheck;
import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;
import kz.mukhamedissa.exchangerates.data.response.ExchangeRateResponse;
import kz.mukhamedissa.exchangerates.network.YahooCurrencyAPIClient;
import kz.mukhamedissa.exchangerates.util.Utils;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRateLoader extends AsyncTaskLoader<ExchangeRateResponse> {

    private static final String BASE_CURRENCY_KEY = "base_currency";
    private static final String SELECTED_CURRENCIES_KEY = "base_currency";

    @NonNull
    private ExchangeRateResponse mResponse;
    @NonNull
    private String mBaseCurrency;
    @NonNull
    private List<String> mRateIds;

    public static Bundle createBundle(@NonNull String baseCurrency, @NonNull ArrayList<String> rateIds) {
        Bundle bundle = new Bundle();
        bundle.putString(BASE_CURRENCY_KEY, baseCurrency);
        bundle.putStringArrayList(SELECTED_CURRENCIES_KEY, rateIds);

        return bundle;
    }

    public ExchangeRateLoader(Context context, Bundle args) {
        super(context);

        mBaseCurrency = args.getString(BASE_CURRENCY_KEY);
        mRateIds = args.getStringArrayList(SELECTED_CURRENCIES_KEY);
    }

    @Override
    protected void onStartLoading() {
        if (mResponse != null) {
            deliverResult(mResponse);

            return;
        }

        forceLoad();
    }

    @Override
    public void deliverResult(ExchangeRateResponse response) {
        mResponse = response;

        if (isStarted()) {
            super.deliverResult(response);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mResponse = null;
    }

    @Override
    public ExchangeRateResponse loadInBackground() {
        try {
            List<ExchangeRate> exchangeRates = YahooCurrencyAPIClient.getExchangeRates(mRateIds);

            return new ExchangeRateResponse(exchangeRates);
        } catch (UnsupportedEncodingException e) {
           return new ExchangeRateResponse(e);
        }
    }
}
