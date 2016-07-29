package com.example.vdovin.bankproject.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

/**
 * Created by vdovin on 6/13/16.
 */

@DatabaseTable(tableName = BankExchangeRate.EXCHANGE_RATE_TABLE_FIELD)
public class BankExchangeRate {

    public static final String EXCHANGE_RATE_ID_FIELD = "id";
    public static final String EXCHANGE_RATE_TABLE_FIELD = "bank_exchange_rate";
    public static final String EXCHANGE_RATE_BANK_ID_FIELD = "bank_id";
    public static final String EXCHANGE_RATE_CURRENCY_ID_FIELD = "currency_id";
    public static final String EXCHANGE_RATE_SELLING_PRICE_FIELD = "selling_price";
    public static final String EXCHANGE_RATE_BUYING_PRICE_FIELD = "buying_price";
    public static final String EXCHANGE_RATE_TIMESTAMP_FIELD = "timestamp";

    @DatabaseField(columnName = EXCHANGE_RATE_ID_FIELD, id = true)
    private Long id;
    @DatabaseField(columnName = EXCHANGE_RATE_BANK_ID_FIELD)
    private Long bankId;
    @DatabaseField(columnName = EXCHANGE_RATE_CURRENCY_ID_FIELD)
    private Long currencyId;
    @DatabaseField(columnName = EXCHANGE_RATE_SELLING_PRICE_FIELD)
    private BigDecimal sellingPrice;
    @DatabaseField(columnName = EXCHANGE_RATE_BUYING_PRICE_FIELD)
    private BigDecimal buyingPrice;
    @DatabaseField(columnName = EXCHANGE_RATE_TIMESTAMP_FIELD)
    private Long timestamp;

    public BankExchangeRate(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Bank bank) {
        bankId = bank.getId();
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currency currency) {
        currencyId = currency.getId();
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
