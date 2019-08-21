package com.android.batdemir.mylibrary.Tools.Spinner;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.util.Log;

import com.android.batdemir.mylibrary.MySpinner;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class HelperSpinner<T> {
    private String TAG = HelperSpinner.class.getSimpleName();
    private Context context;
    private MySpinner mySpinner;
    private Boolean addFirstItem;
    private String firstItemName;

    public HelperSpinner(Context context, MySpinner mySpinner, Boolean addFirstItem, String firstItemName) {
        this.context = context;
        this.mySpinner = mySpinner;
        this.addFirstItem = addFirstItem;
        this.firstItemName = firstItemName;
    }

    public void fill_spinnerCustomModel(ArrayList<T> models,
                                        Field propertyId,
                                        Field propertyDescription){
        try {
            AdapterSpinner<T> adapterSpinner = new AdapterSpinner<>(context, models, propertyId, propertyDescription);
            mySpinner.getSpinner().setAdapter(adapterSpinner);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            new AdapterSpinner<T>(context, new ArrayList<>(), null, null);
        }
    }

    public void fill_spinnerStringList(@ArrayRes int arrayId){
        try {
            Resources res = context.getResources();
            String[] strings = res.getStringArray(arrayId);
            ArrayList<ModelSpinner> tempSpinnerModels = new ArrayList<>();
            if(addFirstItem){
                tempSpinnerModels.add(0,new ModelSpinner(-1,firstItemName));
            }
            for(int i=0;i<strings.length-1;i++){
                tempSpinnerModels.add(new ModelSpinner(i,strings[i]));
            }
            AdapterSpinner<ModelSpinner> adapterSpinner = new AdapterSpinner<>(
                    context,
                    tempSpinnerModels,
                    ModelSpinner.class.getDeclaredField("Id"),
                    ModelSpinner.class.getDeclaredField("Description")
            );
            mySpinner.getSpinner().setAdapter(adapterSpinner);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            new AdapterSpinner<ModelSpinner>(context, new ArrayList<>(), null, null);
        }
    }

    public void fill_spinnerStringArray(ArrayList<String> models){
        try {
            ArrayList<ModelSpinner> tempSpinnerModels = new ArrayList<>();
            if(addFirstItem){
                tempSpinnerModels.add(0,new ModelSpinner(-1,firstItemName));
            }
            for(int i=0;i<models.size();i++){
                tempSpinnerModels.add(new ModelSpinner(i,models.get(i)));
            }
            AdapterSpinner<ModelSpinner> adapterSpinner = new AdapterSpinner<>(
                    context,tempSpinnerModels,
                    ModelSpinner.class.getDeclaredField("Id"),
                    ModelSpinner.class.getDeclaredField("Description")
            );
            mySpinner.getSpinner().setAdapter(adapterSpinner);
        }catch (Exception e ){
            Log.e(TAG,e.getMessage());
            new AdapterSpinner<ModelSpinner>(context, new ArrayList<>(), null, null);
        }
    }

}
