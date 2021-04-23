package com.woow.tlcoperador.ui.Monedero;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MonederoViewModel {

    private MutableLiveData<String> mText;

    public MonederoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
