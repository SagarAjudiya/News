package com.simform.news.ui.sources.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.simform.news.R
import com.simform.news.data.model.Source
import com.simform.news.databinding.FragmentSourcesBinding
import com.simform.news.ui.sources.viewmodel.SourcesVM
import com.simform.news.util.NetworkStateViewModel
import com.simform.news.util.SpacingItemDecoration

class SourcesFragment : Fragment() {

    private lateinit var binding: FragmentSourcesBinding
    private val sourceList = ArrayList<Source>()
    private val newsSourceAdapter = SourceAdapter()
    private val viewModel: SourcesVM by activityViewModels()
    private lateinit var networkStateViewModel: NetworkStateViewModel
    private var isConnection = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_sources,
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

        binding.rvSources.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsSourceAdapter
            addItemDecoration(SpacingItemDecoration(verticalSpace = 10))
        }
    }

    private fun loadNews() {
        binding.pbLoader.visibility = View.VISIBLE

        viewModel.getSources()
        viewModel.resources.observe(viewLifecycleOwner) { sourceArrayList ->
            if (isConnection) {
                binding.pbLoader.visibility = View.VISIBLE
            } else {
                binding.pbLoader.visibility = View.GONE
            }
            binding.pbLoader.visibility = View.GONE
            sourceList.addAll(sourceArrayList)
            newsSourceAdapter.submitList(sourceList)
        }
    }

}