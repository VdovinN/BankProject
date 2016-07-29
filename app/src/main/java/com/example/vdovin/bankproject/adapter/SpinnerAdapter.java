package com.example.vdovin.bankproject.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by vdovin on 6/22/16.
 */
public class SpinnerAdapter extends Spinner {

    OnItemSelectedListener listener;
    int prevPos = -1;
    public SpinnerAdapter(Context context) {
        super(context);
    }

    public SpinnerAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerAdapter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        if (position == getSelectedItemPosition() && prevPos == position) {
            getOnItemSelectedListener().onItemSelected(null, null, position, 0);
        }
        prevPos = position;
    }
}