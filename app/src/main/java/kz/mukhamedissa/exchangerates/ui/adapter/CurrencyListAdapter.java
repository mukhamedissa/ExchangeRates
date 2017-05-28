package kz.mukhamedissa.exchangerates.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kz.mukhamedissa.exchangerates.R;
import kz.mukhamedissa.exchangerates.data.model.CurrencyCheck;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class CurrencyListAdapter extends ArrayAdapter<CurrencyCheck> {

    private List<CurrencyCheck> mCurrencyList;
    private boolean [] mCheckBoxState;

    public CurrencyListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<CurrencyCheck> objects) {
        super(context, resource, objects);
        mCurrencyList = new ArrayList<>();
        mCurrencyList.addAll(objects);

        mCheckBoxState = new boolean[mCurrencyList.size()];
    }

    private class ViewHolder {
        CheckBox currencyCheckBox;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_currencies_list_item, null, false);

            viewHolder = new ViewHolder();

            viewHolder.currencyCheckBox =
                    (CheckBox) convertView.findViewById(R.id.layout_currencies_list_item_checkbox);
            convertView.setTag(viewHolder);

            viewHolder.currencyCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view ;
                    CurrencyCheck currencyCheck = (CurrencyCheck) cb.getTag();
                    currencyCheck.setChecked(cb.isChecked());
                }
            });

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.currencyCheckBox.setText(mCurrencyList.get(position).toString());
        viewHolder.currencyCheckBox.setChecked(mCurrencyList.get(position).isChecked());
        viewHolder.currencyCheckBox.setTag(mCurrencyList.get(position));


        return convertView;
    }

    public List<CurrencyCheck> getCurrencyList() {
        return mCurrencyList;
    }
}
