package com.ostapenko.android.giphygifsshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GiphyViewModel : ViewModel() {

    private var _gifItemLiveData: MutableLiveData<List<GifsItem>> = MutableLiveData()
    val gifItemLiveData: LiveData<List<GifsItem>> get() = _gifItemLiveData

    init {
        viewModelScope.launch {
            _gifItemLiveData.postValue(GiphyRepository().fetchContents().value)
        }
    }


}