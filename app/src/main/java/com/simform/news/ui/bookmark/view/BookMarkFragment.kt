package com.simform.news.ui.bookmark.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.simform.news.R
import com.simform.news.data.model.Article
import com.simform.news.databinding.FragmentBookMarkBinding
import com.simform.news.ui.headlines.view.BottomDialogFragment
import com.simform.news.ui.headlines.view.HeadlinesAdapter
import com.simform.news.ui.newsdetails.view.ArticleFragment
import com.simform.news.util.SpacingItemDecoration

class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding
    private val bookMarkAdapter = HeadlinesAdapter()
    private val articleFragment = ArticleFragment()
    private val bottomDialogFragment = BottomDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_book_mark,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (Article.bookmarkList.isEmpty()) {
            binding.animBookmark.visibility = View.VISIBLE
        } else {
            binding.animBookmark.visibility = View.INVISIBLE
        }

        binding.rvBookmark.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookMarkAdapter
            bookMarkAdapter.submitList(Article.bookmarkList)
            addItemDecoration(SpacingItemDecoration(verticalSpace = 10))
        }
    }

}