package kz.mukhamedissa.exchangerates.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class Result {

    @SerializedName("rate")
    @Expose
    private List<ExchangeRate> rate = null;

    public List<ExchangeRate> getRate() {
        return rate;
    }

    public void setRate(List<ExchangeRate> rate) {
        this.rate = rate;
    }
}
