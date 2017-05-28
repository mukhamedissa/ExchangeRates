package kz.mukhamedissa.exchangerates.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class JSONResponse {

    @SerializedName("query")
    @Expose
    private Query mQuery;

    public Query getQuery() {
        return mQuery;
    }

    public void setQuery(Query query) {
        this.mQuery = query;
    }
}
