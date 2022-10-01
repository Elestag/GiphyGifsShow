package com.ostapenko.android.giphygifsshow.api

import com.google.gson.annotations.SerializedName
import com.ostapenko.android.giphygifsshow.GifsItem

class GifResponse {
    @SerializedName("original")
    lateinit var gifItems: List<GifsItem>
}