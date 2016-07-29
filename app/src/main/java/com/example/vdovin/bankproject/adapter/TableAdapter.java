package com.example.vdovin.bankproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;

import java.util.List;

/**
 * Created by vdovin on 6/19/16.
 */
public class TableAdapter extends BaseAdapter {

    private Context context;
    private List<Bank> bankList;
    private List<BankExchangeRate> bankExchangeRateList;
    private LayoutInflater inflater;

    public TableAdapter(Context context, List<Bank> bank, List<BankExchangeRate> bankExchangeRates) {
        this.context = context;
        bankList = bank;
        bankExchangeRateList = bankExchangeRates;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    private class ViewHolder {

        private TextView name, buy, sell;

    }

    @Override
    public int getCount() {
        return bankList.size();
    }

    @Override
    public Object getItem(int position) {
        return bankList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (convertView == null) {

            row = inflater.inflate(R.layout.exchange_bank_row, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.bank_name);

            viewHolder.buy = (TextView) row.findViewById(R.id.c_buy);
            viewHolder.sell= (TextView) row.findViewById(R.id.c_sell);

            row.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) row.getTag();
        viewHolder.name.setText(bankList.get(position).getName());

        if(bankExchangeRateList.size()> position){
            viewHolder.buy.setText(bankExchangeRateList.get(position).getBuyingPrice().toString());
            viewHolder.sell.setText(bankExchangeRateList.get(position).getSellingPrice().toString());
        }


      //  viewHolder.buy.setText(bankExchangeRateList.get(0).getBuyingPrice().toString());
       // viewHolder.sell.setText(bankExchangeRateList.get(0).getSellingPrice().toString());


        return row;
    }


}
