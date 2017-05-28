package kz.mukhamedissa.exchangerates.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRate {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("Name")
    @Expose
    private String mName;
    @SerializedName("Rate")
    @Expose
    private String mRate;
    @SerializedName("Date")
    @Expose
    private String mDate;
    @SerializedName("Time")
    @Expose
    private String mTime;
    @SerializedName("Ask")
    @Expose
    private String mAsk;
    @SerializedName("Bid")
    @Expose
    private String mBid;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getRate() {
        return mRate;
    }

    public void setRate(String rate) {
        this.mRate = rate;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getAsk() {
        return mAsk;
    }

    public void setAsk(String ask) {
        this.mAsk = ask;
    }

    public String getBid() {
        return mBid;
    }

    public void setBid(String bid) {
        this.mBid = bid;
    }
}
