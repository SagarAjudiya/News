package com.simform.news.ui.headlines.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.simform.news.R
import com.simform.news.data.model.Filter
import com.simform.news.databinding.ItemFilterBinding

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private var filterList = ArrayList<Filter>()
    var onItemClick: ((Filter) -> Unit)? = null
    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1

    class FilterViewHolder(val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: Filter) {
            binding.filter = filter
        }

        fun defaultBg() {
            binding.cvFilter.background = ContextCompat.getDrawable(binding.cvFilter.context, R.drawable.bg_capsule_unselected)
            binding.cvFilter.strokeWidth = 0
        }

        fun selectedBg() {
            binding.cvFilter.background = ContextCompat.getDrawable(binding.cvFilter.context, R.drawable.bg_capsule_selected)
            binding.cvFilter.strokeWidth = 4
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val holder = FilterViewHolder(
            ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.itemView.setOnClickListener {
            onItemClick?.let { it(filterList[holder.adapterPosition]) }

            selectedItemPos = holder.adapterPosition
            lastItemSelectedPos = if (lastItemSelectedPos == -1)
                selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }

        return holder
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        if (position == selectedItemPos)
            holder.selectedBg()
        else
            holder.defaultBg()

        holder.bind(filterList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Filter>) {
        filterList = list
        notifyDataSetChanged()
    }

}