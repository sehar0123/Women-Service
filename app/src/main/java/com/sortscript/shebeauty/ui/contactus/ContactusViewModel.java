package com.sortscript.shebeauty.ui.contactus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Shebeauty");
    }

    public LiveData<String> getText() {
        return mText;
    }
}