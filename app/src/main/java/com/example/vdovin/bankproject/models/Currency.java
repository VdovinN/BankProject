package com.example.vdovin.bankproject.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by vdovin on 6/13/16.
 */
@DatabaseTable(tableName = Currency.CURRENCY_TABLE_FIELD)
public class Currency {

    public static final String CURRENCY_TABLE_FIELD = "currency";
    public static final String CURRENCY_ID_FIELD = "id";
    public static final String CURRENCY_NAME_FIELD = "name";

    @DatabaseField(columnName = CURRENCY_ID_FIELD, id = true)
    private Long id;

    @DatabaseField(columnName = CURRENCY_NAME_FIELD)
    private String name;

    public Currency(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
