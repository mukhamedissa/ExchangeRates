package kz.mukhamedissa.exchangerates.data.response;

import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRateResponse {

    public final List<ExchangeRate> result;

    public final Exception exception;

    public ExchangeRateResponse(List<ExchangeRate> result) {
        this.result = result;
        exception = null;
    }

    public ExchangeRateResponse(Exception e) {
        this.result = null;
        exception = e;
    }

    public boolean isSuccess() {
        return exception == null;
    }
}
