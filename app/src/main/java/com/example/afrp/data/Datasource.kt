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

    fun loadNews(): List<NewsItems> {
//            Log.d(TAG,"IN HERE FIRST")
        val myData: JSONObject? = getData()
//            Log.d(TAG,"IN HERE SECOND")
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
            Log.d(TAG, "IN LOOP")
            val article: JSONObject = news.getJSONObject(i - 1)
            val title: String = article.getString("title")
            val description: String = article.getString("desc")
            val imageUrl: String = article.getString("image")
            val url: String = article.getString("url")
            Log.d(TAG, "HERE $title")
            items.add(NewsItems(title, description, imageUrl, url))
        }
//            println("$items")
        return items

//        return listOf<NewsItems>(
//            NewsItems(R.string.title1, R.string.des1, R.drawable.battlefront_2005, R.string.url1),
//            NewsItems(R.string.title2, R.string.des2, R.drawable.battlefront_2005, R.string.url1),
//            NewsItems(R.string.title3, R.string.des3, R.drawable.battlefront_2005, R.string.url1),
//            NewsItems(R.string.title4, R.string.des4, R.drawable.battlefront_2005, R.string.url1),
//            NewsItems(R.string.title5, R.string.des5, R.drawable.battlefront_2005, R.string.url1),
//            NewsItems(R.string.title6, R.string.des6, R.drawable.battlefront_2005, R.string.url1)
//        )
    }

    private fun getData(): JSONObject? {
        val json =
            URL("https://raw.githubusercontent.com/rgnet1/AFRP/master/json-files/example.json").readText()
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }
}