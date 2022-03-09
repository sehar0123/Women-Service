package com.sortscript.shebeauty.ui.privancypolicy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PolicyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PolicyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Shebeauty");
    }

    public LiveData<String> getText() {
        return mText;
    }
}