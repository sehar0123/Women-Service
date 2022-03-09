package com.sortscript.shebeauty.ui.Feedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedbackModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FeedbackModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Shebeauty");
    }

    public LiveData<String> getText() {
        return mText;
    }
}