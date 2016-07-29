package com.example.vdovin.bankproject.controller;

import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.models.Currency;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdovin on 7/9/16.
 */
public class ParserController {

    private Document doc;
    Element banksRateTable;
    private List<String> banksByName;
    private List<String> currencyByName;
    private List<Bank> banksList;
    private List<Currency> currenciesList;
    private List<BankExchangeRate> bankExchangeRateList;

    private static ParserController mInstance;

    public ParserController(){
        banksList = new ArrayList<>();
        currenciesList = new ArrayList<>();
        bankExchangeRateList = new ArrayList<>();

    }

    public static ParserController getInstance() {
        if (mInstance == null) {
            synchronized (ParserController.class) {
                if (mInstance == null) {
                    mInstance = new ParserController();
                }
            }
        }

        return mInstance;
    }

    public List<Bank> getBanksList() {
        return banksList;
    }

    public List<Currency> getCurrenciesList() {
        return currenciesList;
    }

    public List<BankExchangeRate> getBankExchangeRateList() {
        return bankExchangeRateList;
    }

    private void addBanks() {

        banksByName = new ArrayList<>();

        banksByName.add("bnm");
        banksByName.add("comertbank");
        banksByName.add("energbank");
        banksByName.add("ecb");
        banksByName.add("fincombank");
        banksByName.add("maib");
        banksByName.add("victoriabank");
        banksByName.add("procreditbank");
        banksByName.add("micb");
        banksByName.add("eximbank");
        banksByName.add("bcr");

    }

    private void addCurrencies() {

        currencyByName = new ArrayList<>();

        currencyByName.add("col-USD");
        currencyByName.add("col-EUR");
        currencyByName.add("col-RUB");
        currencyByName.add("col-RON");
        currencyByName.add("col-UAH");
        currencyByName.add("col-GBP");

    }

    public void parse(String response) {

        doc = Jsoup.parse(response, "UTF-8");
        banksRateTable = doc.getElementById("tabelBankValute");

    }

    public void parseResponse(String r) {
        Document doc = Jsoup.parse(r, "UTF-8");
        Element banksRateTable = doc.getElementById("tabelBankValute");

        // ArrayList<Bank> banksList = new ArrayList<>();
        ArrayList<String> banksByName = new ArrayList<>();
        ArrayList<String> banksToRemove = new ArrayList<>();
        ArrayList<String> currencyByName = new ArrayList<>();


        banksByName.add("bnm");
        banksByName.add("comertbank");
        banksByName.add("energbank");
        banksByName.add("ecb");
        banksByName.add("fincombank");
        banksByName.add("maib");
        banksByName.add("victoriabank");

        banksByName.add("procreditbank");
        banksByName.add("micb");
        banksByName.add("eximbank");
        banksByName.add("bcr");

        currencyByName.add("col-USD");
        currencyByName.add("col-EUR");
        currencyByName.add("col-RUB");
        currencyByName.add("col-RON");
        currencyByName.add("col-UAH");
        currencyByName.add("col-GBP");
        //currencyByName.add("col-CHF");
        //currencyByName.add("col-TRY");

        //  String rates = banksRateTable.child(0).getElementsByClass("col-USD").get(0).child(0).text();


        for(String bank : banksByName){

            Elements bankRate = banksRateTable.child(1).getElementsByClass(bank);
            if (!bankRate.isEmpty()) {


                Bank b = new Bank();
                b.setName(banksRateTable.child(1).getElementsByClass(bank).get(0).child(0).text());
                banksList.add(b);
            } else {
                banksToRemove.add(bank);
                //        banksByName.remove(bank);
            }

        }
        banksByName.removeAll(banksToRemove);

        for(String currency : currencyByName){
            Currency c = new Currency();
            c.setName(currency.substring(4, 7));//TODO hardcoded
            currenciesList.add(c);
        }


        for(int i = 0; i < banksByName.size(); i++){
            for(int j = 0; j < currencyByName.size(); j++){

                String currencyBuyingPrice = banksRateTable.child(1).getElementsByClass(banksByName.get(i))
                        .get(0).getElementsByClass(currencyByName.get(j)).get(0).text();
                String currencySellingPrice = banksRateTable.child(1).getElementsByClass(banksByName.get(i))
                        .get(0).getElementsByClass(currencyByName.get(j)).get(1).text();
                if (currencyBuyingPrice.equals("-")) {
                    currencyBuyingPrice = "0";
                }
                if (currencySellingPrice.equals("-")) {
                    currencySellingPrice = "0";
                }
                Long timestamp = System.currentTimeMillis();

                BankExchangeRate bankExchangeRate = new BankExchangeRate();
                //bankExchangeRate.setId();
                //bankExchangeRate.setBankId(banksList.get(i));
                //bankExchangeRate.setCurrencyId(currenciesList.get(j));
                bankExchangeRate.setBuyingPrice(new BigDecimal(currencyBuyingPrice));
                bankExchangeRate.setSellingPrice(new BigDecimal(currencySellingPrice));
                bankExchangeRate.setTimestamp(timestamp);
                bankExchangeRateList.add(bankExchangeRate);


            }
        }

    }

}
