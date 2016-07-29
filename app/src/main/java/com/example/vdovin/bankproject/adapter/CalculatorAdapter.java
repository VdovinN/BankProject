package com.example.vdovin.bankproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.models.BankExchangeRate;

import java.util.List;

/**
 * Created by vdovin on 6/22/16.
 */
public class CalculatorAdapter extends BaseAdapter {

    private List<BankExchangeRate> bankExchangeRateList;
    private Context context;
    private LayoutInflater inflater;


    public CalculatorAdapter(Context context, List<BankExchangeRate> bankExchangeRates){

        this.context = context;
        bankExchangeRateList = bankExchangeRates;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private class ViewHolder {

        private TextView buy, sell;

    }

    @Override
    public int getCount() {
        return bankExchangeRateList.size();
    }

    @Override
    public Object getItem(int position) {
        return bankExchangeRateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (convertView == null) {
            row = inflater.inflate(R.layout.calculator_rate_row, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.buy = (TextView) row.findViewById(R.id.c_buy);
            viewHolder.sell = (TextView) row.findViewById(R.id.c_sell);

            row.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) row.getTag();
        if(position == 0){
            viewHolder.buy.setText("-");
            viewHolder.sell.setText("-");
        } else {

            viewHolder.buy.setText(bankExchangeRateList.get(position).getBuyingPrice().toString());
            viewHolder.sell.setText(bankExchangeRateList.get(position).getSellingPrice().toString());

        }


        return row;
    }
}
