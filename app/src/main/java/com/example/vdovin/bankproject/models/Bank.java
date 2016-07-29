package com.example.vdovin.bankproject.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by vdovin on 6/13/16.
 */
@DatabaseTable(tableName = Bank.BANK_TABLE_FIELD)
public class Bank {

    public static final String BANK_TABLE_FIELD = "bank";
    public static final String BANK_ID_FIELD = "id";
    public static final String BANK_NAME_FIELD = "name";


    @DatabaseField(columnName = BANK_ID_FIELD, id = true)
    private Long id;

    @DatabaseField(columnName = BANK_NAME_FIELD)
    private String name;

    public Bank(){

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
