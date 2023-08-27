package com.simform.news.ui.headlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.simform.news.R
import com.simform.news.data.model.Article
import com.simform.news.data.model.Filter
import com.simform.news.databinding.FragmentHeadlinesBinding
import com.simform.news.ui.headlines.viewmodel.DataViewModel
import com.simform.news.ui.headlines.viewmodel.HeadlinesVM
import com.simform.news.ui.newsdetails.view.ArticleFragment
import com.simform.news.util.NetworkStateViewModel
import com.simform.news.util.SpacingItemDecoration

class HeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesBinding
    private var articleList = ArrayList<Article>()
    private val headlineAdapter = HeadlinesAdapter()
    private val articleFragment = ArticleFragment()
    private val bottomDialogFragment = BottomDialogFragment()
    private val filterAdapter = FilterAdapter()
    private val filterList = Filter.filterList

    private val viewModel: HeadlinesVM by activityViewModels()
    private val dataViewModel: DataViewModel by activityViewModels()
    private lateinit var networkStateViewModel: NetworkStateViewModel
    private var isConnection = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_headlines,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkStateViewModel = ViewModelProvider(this).get(NetworkStateViewModel::class.java)
        networkStateViewModel.getNetworkUpdate()
        initViews()
    }

    /**
     * Init Views`
     */
    private fun initViews() {
        networkStateViewModel.networkState.observe(viewLifecycleOwner) {
            if (it) {
                isConnection = it
                binding.animConnection.visibility = View.GONE
                loadNews()
            } else {
                isConnection = false
                binding.animConnection.visibility = View.VISIBLE
                Snackbar.make(
                    binding.root,
                    getString(R.string.connection_lost),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.rvFilter.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
            filterAdapter.submitList(filterList)
            addItemDecoration(SpacingItemDecoration(horizontalSpace = 10))
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = headlineAdapter
            addItemDecoration(SpacingItemDecoration(verticalSpace = 10))

            headlineAdapter.onItemClick = {
                val bundle = Bundle()
                bundle.putParcelable("article", it)
                parentFragmentManager.setFragmentResult("article", bundle)
//                dataViewModel.dataList.value = it
                articleFragment.show(parentFragmentManager, "ArticleFragment")
            }

            headlineAdapter.onBtnPointClick = {
                val bundle = Bundle()
                bundle.putParcelable("article", it)
                parentFragmentManager.setFragmentResult("bottomSheet", bundle)
                bottomDialogFragment.show(parentFragmentManager, "BottomDialogFragment")
            }
        }
    }

    private fun loadNews() {
        binding.pbLoader.visibility = View.VISIBLE

        viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) { articles ->
            if (isConnection) {
                binding.pbLoader.visibility = View.VISIBLE
            } else {
                binding.pbLoader.visibility = View.GONE
            }
            binding.pbLoader.visibility = View.GONE
            articleList = articles
            headlineAdapter.submitList(articleList)
        }

        filterAdapter.onItemClick = {
            viewModel.category = it.name.lowercase()
            viewModel.getNews()
            if (isConnection) {
                binding.pbLoader.visibility = View.VISIBLE
            } else {
                binding.pbLoader.visibility = View.GONE
                Snackbar.make(
                    binding.root,
                    getString(R.string.connection_lost),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

}