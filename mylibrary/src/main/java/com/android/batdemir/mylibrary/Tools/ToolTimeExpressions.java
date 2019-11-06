package com.android.batdemir.mylibrary.Tools;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolTimeExpressions {

    private static ToolTimeExpressions ourInstance = null;

    private ToolTimeExpressions() {
    }

    public static ToolTimeExpressions getInstance() {
        if (ourInstance == null) {
            ourInstance = new ToolTimeExpressions();
        }
        return ourInstance;
    }

    @SuppressLint("SimpleDateFormat")
    public Date setStringToDate(@NonNull String stringDate, @NonNull GlobalVariable.DateFormat outputFormat) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(outputFormat.toString());
            date = sdf.parse(stringDate);
        } catch (Exception e) {
            Log.e(ToolTimeExpressions.class.getSimpleName(), e.getMessage());
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public String setDateToString(@NonNull Date date, @NonNull GlobalVariable.DateFormat outputFormat) {
        String result = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(outputFormat.toString());
            result = dateFormat.format(date);
        } catch (Exception e) {
            Log.e(ToolTimeExpressions.class.getSimpleName(), e.getMessage());
        }
        return result;
    }

    @SuppressLint("SimpleDateFormat")
    public String setDateFormat(@NonNull String stringDate, @NonNull GlobalVariable.DateFormat inputFormat, @NonNull GlobalVariable.DateFormat outputFormat) {
        String result = "";
        try {
            Date date = setStringToDate(stringDate, inputFormat);
            SimpleDateFormat dateFormat = new SimpleDateFormat(outputFormat.toString());
            result = dateFormat.format(date);
        } catch (Exception e) {
            Log.e(ToolTimeExpressions.class.getSimpleName(), e.getMessage());
        }
        return result;
    }
}
