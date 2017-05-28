package kz.mukhamedissa.exchangerates.ui.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kz.mukhamedissa.exchangerates.R;
import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRateListAdapter extends RecyclerView.Adapter<ExchangeRateListAdapter.ExchangeRateViewHolder> {

    @NonNull
    private List<ExchangeRate> mExchangeRateList;

    public ExchangeRateListAdapter() {
        mExchangeRateList = new ArrayList<>();
    }

    @Override
    public ExchangeRateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_exchange_rate_list, null, false);

        return new ExchangeRateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExchangeRateViewHolder holder, int position) {
        holder.bindExchangeRate(mExchangeRateList.get(position));
    }

    @Override
    public int getItemCount() {
        return mExchangeRateList.size();
    }

    public void setData(@NonNull List<ExchangeRate> exchangeRateList) {
        mExchangeRateList.clear();
        mExchangeRateList.addAll(exchangeRateList);

        notifyDataSetChanged();
    }

    class ExchangeRateViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameView;
        private TextView mRateView;
        private TextView mAskView;
        private TextView mBidView;
        private TextView mDateTimeView;

        public ExchangeRateViewHolder(View itemView) {
            super(itemView);

            mNameView = (TextView) itemView.findViewById(R.id.layout_item_exchange_rate_list_name);
            mRateView = (TextView) itemView.findViewById(R.id.layout_item_exchange_rate_list_rate);
            mAskView = (TextView) itemView.findViewById(R.id.layout_item_exchange_rate_list_ask);
            mBidView = (TextView) itemView.findViewById(R.id.layout_item_exchange_rate_list_bid);
            mDateTimeView = (TextView) itemView.findViewById(R.id.layout_item_exchange_rate_list_datetime);
        }

        public void bindExchangeRate(@NonNull ExchangeRate exchangeRate) {

            Resources resources = itemView.getResources();

            mNameView.setText(exchangeRate.getName());
            mRateView.setText(resources.getString(R.string.rate_string, exchangeRate.getRate()));
            mAskView.setText(resources.getString(R.string.ask_string, exchangeRate.getAsk()));
            mBidView.setText(resources.getString(R.string.bid_string, exchangeRate.getBid()));
            mDateTimeView.setText(resources.getString(R.string.date_string,
                    String.format("%s %s", exchangeRate.getDate(), exchangeRate.getTime())));
        }
    }
}
