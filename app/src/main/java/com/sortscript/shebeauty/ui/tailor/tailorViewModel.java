package com.sortscript.shebeauty.ui.tailor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class tailorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public tailorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}