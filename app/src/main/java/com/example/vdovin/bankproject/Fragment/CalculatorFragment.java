package com.example.vdovin.bankproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.Session;
import com.example.vdovin.bankproject.extra.Utils;
import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdovin on 6/20/16.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {


    private Spinner spBank;
    private ArrayAdapter<String> spAdapter;

    private List<Bank> bankList;
    private List<String> nameOfBankList;
    private List<BankExchangeRate> bankExchangeRateList;

    private TextView usBuy, usSell, euBuy, euSell, ruBuy, ruSell, roBuy, roSell, uaBuy, uaSell, gbBuy, gbSell;
    private EditText mdCalc, usCalc, euCalc, ruCalc, roCalc, uaCalc, gbCalc;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calculator_layout, container, false);

        spBank = (Spinner) v.findViewById(R.id.bank_sp);

        mdCalc = (EditText) v.findViewById(R.id.md_calc);
        usCalc = (EditText) v.findViewById(R.id.us_calc);
        euCalc = (EditText) v.findViewById(R.id.eu_calc);
        ruCalc = (EditText) v.findViewById(R.id.ru_calc);
        roCalc = (EditText) v.findViewById(R.id.ro_calc);
        uaCalc = (EditText) v.findViewById(R.id.ua_calc);
        gbCalc = (EditText) v.findViewById(R.id.gb_calc);

        usBuy = (TextView) v.findViewById(R.id.us_buy);
        usSell = (TextView) v.findViewById(R.id.us_sell);
        euBuy = (TextView) v.findViewById(R.id.eu_buy);
        euSell = (TextView) v.findViewById(R.id.eu_sell);
        ruBuy = (TextView) v.findViewById(R.id.ru_buy);
        ruSell = (TextView) v.findViewById(R.id.ru_sell);
        roBuy = (TextView) v.findViewById(R.id.ro_buy);
        roSell = (TextView) v.findViewById(R.id.ro_sell);
        uaBuy = (TextView) v.findViewById(R.id.ua_buy);
        uaSell = (TextView) v.findViewById(R.id.ua_sell);
        gbBuy = (TextView) v.findViewById(R.id.gb_buy);
        gbSell = (TextView) v.findViewById(R.id.gb_sell);

        bankExchangeRateList = new ArrayList<>();
        nameOfBankList = new ArrayList<>();
        bankList = new ArrayList<>();

        bankExchangeRateList = Session.loadRatesByBankId(getActivity(), 1L);
        updateBankRates(bankExchangeRateList);
        bankList = Session.loadAllBanks(getActivity());


        for (Bank b : bankList) {
            nameOfBankList.add(b.getName());
        }


        spAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, nameOfBankList);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBank.setAdapter(spAdapter);

        spBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bankExchangeRateList.clear();
                List<BankExchangeRate> b = Session.loadRatesByBankId(getActivity(), (long) (position + 1));
                bankExchangeRateList.addAll(b);
                updateBankRates(bankExchangeRateList);

                mdCalc.setText("");
                usCalc.setText("");
                euCalc.setText("");
                ruCalc.setText("");
                roCalc.setText("");
                uaCalc.setText("");
                gbCalc.setText("");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mdCalc.setOnClickListener(this);
        usCalc.setOnClickListener(this);
        euCalc.setOnClickListener(this);
        ruCalc.setOnClickListener(this);
        roCalc.setOnClickListener(this);
        uaCalc.setOnClickListener(this);
        gbCalc.setOnClickListener(this);

        mdCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mdCalc.isFocused())
                    if (mdCalc.getText().length() > 0)
                        convert(s, mdCalc);
                    else {
                        usCalc.setText("");
                        euCalc.setText("");
                        ruCalc.setText("");
                        roCalc.setText("");
                        uaCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }
            }
        });

        usCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (usCalc.isFocused())
                    if (usCalc.getText().length() > 0)
                        convert(s, usCalc);
                    else {

                        mdCalc.setText("");
                        euCalc.setText("");
                        ruCalc.setText("");
                        roCalc.setText("");
                        uaCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }

            }
        });

        euCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (euCalc.isFocused())
                    if (euCalc.getText().length() > 0)
                        convert(s, euCalc);
                    else {

                        mdCalc.setText("");
                        usCalc.setText("");
                        ruCalc.setText("");
                        roCalc.setText("");
                        uaCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }

            }
        });

        ruCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ruCalc.isFocused())
                    if (ruCalc.getText().length() > 0)
                        convert(s, ruCalc);
                    else {

                        mdCalc.setText("");
                        usCalc.setText("");
                        euCalc.setText("");
                        roCalc.setText("");
                        uaCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }

            }
        });


        roCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (roCalc.isFocused())
                    if (roCalc.getText().length() > 0)
                        convert(s, roCalc);
                    else {

                        mdCalc.setText("");
                        usCalc.setText("");
                        euCalc.setText("");
                        ruCalc.setText("");
                        uaCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }

            }
        });


        uaCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (uaCalc.isFocused())
                    if (uaCalc.getText().length() > 0)
                        convert(s, uaCalc);
                    else {

                        mdCalc.setText("");
                        usCalc.setText("");
                        euCalc.setText("");
                        ruCalc.setText("");
                        roCalc.setText("");
                        gbCalc.setText("");
                        return;
                    }

            }
        });

        gbCalc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (gbCalc.isFocused())
                    if (gbCalc.getText().length() > 0)
                        convert(s, gbCalc);
                    else {

                        mdCalc.setText("");
                        usCalc.setText("");
                        euCalc.setText("");
                        ruCalc.setText("");
                        roCalc.setText("");
                        uaCalc.setText("");
                        return;
                    }

            }
        });


        return v;
    }

    public void updateBankRates(List<BankExchangeRate> bankExchangeRates) {


        if(bankExchangeRates.size() > 0) {
            usBuy.setText(bankExchangeRates.get(0).getBuyingPrice().toString());
            usSell.setText(bankExchangeRates.get(0).getSellingPrice().toString());
            euBuy.setText(bankExchangeRates.get(1).getBuyingPrice().toString());
            euSell.setText(bankExchangeRates.get(1).getSellingPrice().toString());
            ruBuy.setText(bankExchangeRates.get(2).getBuyingPrice().toString());
            ruSell.setText(bankExchangeRates.get(2).getSellingPrice().toString());
            roBuy.setText(bankExchangeRates.get(3).getBuyingPrice().toString());
            roSell.setText(bankExchangeRates.get(3).getSellingPrice().toString());
            uaBuy.setText(bankExchangeRates.get(4).getBuyingPrice().toString());
            uaSell.setText(bankExchangeRates.get(4).getSellingPrice().toString());
            gbBuy.setText(bankExchangeRates.get(5).getBuyingPrice().toString());
            gbSell.setText(bankExchangeRates.get(5).getSellingPrice().toString());
        } else {
            mdCalc.setEnabled(false);
            usCalc.setEnabled(false);
            euCalc.setEnabled(false);
            ruCalc.setEnabled(false);
            roCalc.setEnabled(false);
            uaCalc.setEnabled(false);
            gbCalc.setEnabled(false);
        }
    }

    public void convert(Editable s, EditText editText) {


        BigDecimal currentValue = new BigDecimal(s.toString());

        switch (editText.getId()) {

            case R.id.md_calc:

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(currentValue.divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(currentValue.divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(currentValue.divide(bankExchangeRateList.get(2).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(currentValue.divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(currentValue.divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(currentValue.divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                    /*usCalc.setText(bankExchangeRateList.get(0).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                    euCalc.setText(bankExchangeRateList.get(1).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                    ruCalc.setText(bankExchangeRateList.get(2).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                    roCalc.setText(bankExchangeRateList.get(3).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                    uaCalc.setText(bankExchangeRateList.get(4).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                    gbCalc.setText(bankExchangeRateList.get(5).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());*/

                break;
            case R.id.us_calc:

                    mdCalc.setText(bankExchangeRateList.get(0).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(bankExchangeRateList.get(0).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(bankExchangeRateList.get(0).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(2).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(bankExchangeRateList.get(0).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(bankExchangeRateList.get(0).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(bankExchangeRateList.get(0).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                break;

            case R.id.eu_calc:

                mdCalc.setText(bankExchangeRateList.get(1).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(bankExchangeRateList.get(1).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(bankExchangeRateList.get(1).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(2).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(bankExchangeRateList.get(1).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(bankExchangeRateList.get(1).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(bankExchangeRateList.get(1).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                break;
            case R.id.ru_calc:

                mdCalc.setText(bankExchangeRateList.get(2).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(bankExchangeRateList.get(2).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(bankExchangeRateList.get(2).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(bankExchangeRateList.get(2).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(bankExchangeRateList.get(2).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(bankExchangeRateList.get(2).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                break;
            case R.id.ro_calc:

                mdCalc.setText(bankExchangeRateList.get(3).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(bankExchangeRateList.get(3).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(bankExchangeRateList.get(3).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(bankExchangeRateList.get(3).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(2).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(bankExchangeRateList.get(3).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(bankExchangeRateList.get(3).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                break;
            case R.id.ua_calc:


                mdCalc.setText(bankExchangeRateList.get(4).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(bankExchangeRateList.get(4).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());
                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(bankExchangeRateList.get(4).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(bankExchangeRateList.get(4).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(2).getBuyingPrice(), 4,BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(bankExchangeRateList.get(4).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(5).getBuyingPrice().equals(BigDecimal.ZERO))
                    gbCalc.setText(bankExchangeRateList.get(4).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(5).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    gbCalc.setText(BigDecimal.ZERO.toString());

                break;
            case R.id.gb_calc:

                mdCalc.setText(bankExchangeRateList.get(5).getBuyingPrice().multiply(currentValue).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());

                if (!bankExchangeRateList.get(0).getBuyingPrice().equals(BigDecimal.ZERO))
                    usCalc.setText(bankExchangeRateList.get(5).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(0).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    usCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(1).getBuyingPrice().equals(BigDecimal.ZERO))
                    euCalc.setText(bankExchangeRateList.get(5).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(1).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    euCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(2).getBuyingPrice().equals(BigDecimal.ZERO))
                    ruCalc.setText(bankExchangeRateList.get(5).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(2).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    ruCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(3).getBuyingPrice().equals(BigDecimal.ZERO))
                    roCalc.setText(bankExchangeRateList.get(5).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(3).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    roCalc.setText(BigDecimal.ZERO.toString());

                if (!bankExchangeRateList.get(4).getBuyingPrice().equals(BigDecimal.ZERO))
                    uaCalc.setText(bankExchangeRateList.get(5).getSellingPrice().multiply(currentValue).divide(bankExchangeRateList.get(4).getBuyingPrice(), 4, BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN).toString());
                else
                    uaCalc.setText(BigDecimal.ZERO.toString());

                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.hideKeyboard(getActivity());
    }

    @Override
    public void onClick(View v) {
        mdCalc.setText("");
        usCalc.setText("");
        euCalc.setText("");
        ruCalc.setText("");
        roCalc.setText("");
        uaCalc.setText("");
        gbCalc.setText("");
    }
}



