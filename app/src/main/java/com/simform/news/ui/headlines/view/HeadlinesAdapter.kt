package com.simform.news.ui.headlines.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.news.data.model.Article
import com.simform.news.databinding.ItemHeadlinesBinding

class HeadlinesAdapter : RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder>() {

    private var articleList = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null
    var onBtnPointClick: ((Article) -> Unit)? = null

    class HeadlinesViewHolder(val binding: ItemHeadlinesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        val holder = HeadlinesViewHolder(
            ItemHeadlinesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.itemView.setOnClickListener {
            onItemClick?.let { it -> it(articleList[holder.adapterPosition]) }
        }

        holder.binding.btnPoint.setOnClickListener {
            onBtnPointClick?.let { it -> it(articleList[holder.adapterPosition]) }
        }

        return holder
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Article>) {
        articleList = list
        notifyDataSetChanged()
    }
}