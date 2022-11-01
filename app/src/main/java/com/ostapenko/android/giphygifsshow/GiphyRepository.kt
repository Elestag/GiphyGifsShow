package com.ostapenko.android.giphygifsshow

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.ostapenko.android.giphygifsshow.api.GifResponse
import com.ostapenko.android.giphygifsshow.api.GiphyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "GiphyRepository"

class GiphyRepository {
    private val giphyApi: GiphyApi

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(GifResponse::class.java, GiphyDeserializer())
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        giphyApi = retrofit.create(GiphyApi::class.java)
    }

    suspend fun fetchContents(): MutableLiveData<List<GifsItem>> {
        val responseLiveData: MutableLiveData<List<GifsItem>> = MutableLiveData()

        val liveData = CoroutineScope(Dispatchers.IO).async {
            val response = giphyApi.fetchContents()

            // Log.d(TAG, "Response received: $response")
            val gifResponse: GifResponse? = response.body()
            gifResponse?.gifItems = response.body()?.gifItems!!
            var gifItems: List<GifsItem> = gifResponse?.gifItems ?: mutableListOf()
            // Log.d(TAG, "gifItems : $gifItems")
            gifItems = gifItems.filterNot {
                it.url.isBlank()
            }
            responseLiveData.postValue(gifItems)
        }

        liveData.await()
        // Log.d(TAG, "ResponseLiveData : ${responseLiveData.value}")
        return responseLiveData
    }
}