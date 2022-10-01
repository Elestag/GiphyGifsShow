package com.ostapenko.android.giphygifsshow

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ostapenko.android.giphygifsshow.api.GifResponse
import java.lang.reflect.Type

private const val TAG = "Deserializer"

class GiphyDeserializer : JsonDeserializer<GifResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GifResponse {
        val gifResponse = GifResponse()
        val resultList: MutableList<GifsItem> = mutableListOf()

        val jsonObject: JsonObject? = json?.asJsonObject
        //Log.d(TAG, "JsonObject - $jsonObject")

        val jsonObjectData =
            jsonObject?.asJsonObject?.get("data")?.asJsonArray
        // Log.d(TAG, "JsonObject - $jsonObjectData")

        if (jsonObjectData != null) {
            for (e in 0 until jsonObjectData.size()) {
                val urlFromJson = jsonObjectData.asJsonArray
                    .get(e).asJsonObject
                    .get("images")?.asJsonObject
                    ?.get("original")?.asJsonObject
                    ?.get("url").toString().replace("\"", "")
                resultList += GifsItem(url = urlFromJson)
                Log.d(TAG, "url from json - $urlFromJson")
            }
        }
        gifResponse.gifItems = resultList
        Log.d(TAG, "resultList - $resultList")
        return gifResponse
    }
}