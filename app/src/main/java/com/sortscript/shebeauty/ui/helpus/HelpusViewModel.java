package com.sortscript.shebeauty.ui.helpus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Shebeauty");
    }

    public LiveData<String> getText() {
        return mText;
    }
}