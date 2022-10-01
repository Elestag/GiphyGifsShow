package com.ostapenko.android.giphygifsshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class GiphyViewModel : ViewModel() {

    val gifItemLiveData: LiveData<List<GifsItem>>

    init {
        gifItemLiveData = GiphyRepository().fetchContents()
    }
}