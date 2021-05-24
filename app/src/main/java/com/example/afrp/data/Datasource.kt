package com.example.afrp.data

import com.example.afrp.R
import com.example.afrp.model.NewsItems

class Datasource {

    fun loadNews(): List<NewsItems>{
        return listOf<NewsItems>(
            NewsItems(R.string.title1, R.string.des1, R.drawable.battlefront_2005, R.string.url1),
            NewsItems(R.string.title2, R.string.des2, R.drawable.battlefront_2005, R.string.url1),
            NewsItems(R.string.title3, R.string.des3, R.drawable.battlefront_2005, R.string.url1),
            NewsItems(R.string.title4, R.string.des4, R.drawable.battlefront_2005, R.string.url1),
            NewsItems(R.string.title5, R.string.des5, R.drawable.battlefront_2005, R.string.url1),
            NewsItems(R.string.title6, R.string.des6, R.drawable.battlefront_2005, R.string.url1)
        )
    }
}