package kz.mukhamedissa.exchangerates.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class Query {

    @SerializedName("count")
    @Expose
    private Integer mCount;
    @SerializedName("created")
    @Expose
    private String mCreated;
    @SerializedName("lang")
    @Expose
    private String mLang;
    @SerializedName("results")
    @Expose
    private Result mResult;

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer count) {
        this.mCount = count;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        this.mCreated = created;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        this.mLang = lang;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        this.mResult = result;
    }
}
