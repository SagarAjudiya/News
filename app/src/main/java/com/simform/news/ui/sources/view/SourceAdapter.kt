package com.simform.news.ui.sources.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.news.data.model.Source
import com.simform.news.databinding.ItemNewsSourceBinding

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private var newsSourceList = ArrayList<Source>()

    class SourceViewHolder(val binding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsSource: Source) {
            binding.newsSource = newsSource
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val holder = SourceViewHolder(
            ItemNewsSourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int {
        return newsSourceList.size
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(newsSourceList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Source>) {
        newsSourceList = list
        notifyDataSetChanged()
    }

}