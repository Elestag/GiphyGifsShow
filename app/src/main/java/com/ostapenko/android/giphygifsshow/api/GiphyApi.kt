package com.ostapenko.android.giphygifsshow.api

import com.ostapenko.android.giphygifsshow.GifsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GiphyApi {

    @GET("v1/gifs/search?" +
            "api_key=1IEXtp8OYBOk0hRizCDSCc5HHNHxG0Ui" +
            "&q=deadpool" +
            "&limit=27" +
            "&offset=0" +
            "&rating=g" +
            "&lang=en")
   suspend fun fetchContents(): Response<GifResponse>
}
