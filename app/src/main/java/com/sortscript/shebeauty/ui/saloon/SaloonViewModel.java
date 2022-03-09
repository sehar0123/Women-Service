package com.sortscript.shebeauty.ui.saloon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaloonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SaloonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}