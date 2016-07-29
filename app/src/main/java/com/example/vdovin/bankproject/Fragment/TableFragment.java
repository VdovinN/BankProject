package com.example.vdovin.bankproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.Session;
import com.example.vdovin.bankproject.adapter.TableAdapter;
import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.models.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdovin on 6/20/16.
 */
public class TableFragment extends Fragment {

    private List<Bank> bankList;
    private List<Currency> currencyList;
    private List<String> nameOfCurrencyList;
    private ListView displayList;

    private List<BankExchangeRate> bankExchangeRateList;

    private Spinner spCurrency;

    private TableAdapter tableAdapter;

    private ArrayAdapter<String> spAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.currency_table_layout, container, false);

        displayList = (ListView) v.findViewById(R.id.bank_list);
        spCurrency = (Spinner) v.findViewById(R.id.currency_choice);

        bankList = new ArrayList<>();
        currencyList = new ArrayList<>();
        nameOfCurrencyList = new ArrayList<>();

        bankList = Session.loadAllBanks(getActivity());
        bankExchangeRateList = Session.loadRatesByCurrencyId(getActivity(), 1L);//default
        tableAdapter = new TableAdapter(getActivity(), bankList, bankExchangeRateList);
        currencyList = Session.loadAllCurrencies(getActivity());
        displayList.setAdapter(tableAdapter);

        displayList.setEnabled(false);
        displayList.setOnItemClickListener(null);

        for(Currency c : currencyList){
            nameOfCurrencyList.add(c.getName());
        }

        //tableAdapter = new TableAdapter(getActivity(), bankList);
        spAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, nameOfCurrencyList);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(spAdapter);

        spCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bankExchangeRateList.clear();
                List<BankExchangeRate> b = Session.loadRatesByCurrencyId(getActivity(), (long)(position+1));
                bankExchangeRateList.addAll(b);
                tableAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }
}
