package com.simform.news.ui.headlines.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.simform.news.R
import com.simform.news.data.model.Article
import com.simform.news.databinding.FragmentBottomDialogBinding

class BottomDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_bottom_dialog,
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
        parentFragmentManager.setFragmentResultListener(
            "bottomSheet",
            viewLifecycleOwner
        ) { _, bundle ->
            val res = bundle.getParcelable<Article>("article")
            binding.apply {
                article = res
            }
        }

        binding.apply {
            tvShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, article?.url)
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share with"))
                dismiss()
            }

            tvSave.setOnClickListener {
                if (Article.bookmarkList.contains(binding.article ?: Article())) {
                    Article.bookmarkList.remove(binding.article ?: Article())
                } else {
                    Article.bookmarkList.add(binding.article ?: Article())
                }
                dismiss()
            }

            tvBrowser.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(binding.article?.url)
                    )
                )
                dismiss()
            }
        }
    }

}