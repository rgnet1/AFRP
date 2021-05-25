package com.example.afrp.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.afrp.model.NewsItems
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class Datasource {
    fun loadEmpty(): List<NewsItems> {
        return listOf<NewsItems>(
            NewsItems("tmp", "tmp_dec", "tmp_image", "tmp_url")
        )
    }

    fun loadNews(url: String): List<NewsItems> {
        val myData: JSONObject? = getData(url)
        println("HELLO There. after second")
        println("MY DATA IS: $myData")
        if (myData == null) {
            Log.d(TAG, "Returning NULL")
            return mutableListOf()

        }
//            Log.d(TAG,"IN HERE 3")
        val items = mutableListOf<NewsItems>()
        val news: JSONArray = myData.getJSONArray("articles")
//            Log.d(TAG,"HERENEWS $news")
        for (i in 1..news.length()) {
            val article: JSONObject = news.getJSONObject(i - 1)
            val title: String = article.getString("title")
            val description: String = article.getString("desc")
            val imageUrl: String = article.getString("image")
            val url: String = article.getString("url")
            items.add(NewsItems(title, description, imageUrl, url))
        }
        return items

    }

    private fun getData(url: String): JSONObject? {
        val json =
            URL(url).readText()
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }
}