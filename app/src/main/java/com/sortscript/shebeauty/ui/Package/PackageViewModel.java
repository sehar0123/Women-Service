package com.sortscript.shebeauty.ui.Package;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PackageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PackageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gym fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}