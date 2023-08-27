package com.simform.news.data.model

import com.simform.news.R

data class Filter(
    val image: Int,
    val name: String
) {
    companion object {
        val filterList = arrayListOf(
            Filter(R.drawable.general, "general"),
            Filter(R.drawable.business, "business"),
            Filter(R.drawable.sports, "sports"),
            Filter(R.drawable.health, "health"),
            Filter(R.drawable.entertainment, "entertainment"),
            Filter(R.drawable.technology, "technology"),
            Filter(R.drawable.science, "science"),
        )
    }
}