package com.simform.news.ui.newsdetails.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.simform.news.R
import com.simform.news.data.model.Article
import com.simform.news.databinding.FragmentArticleBinding
import com.simform.news.ui.headlines.viewmodel.DataViewModel


class ArticleFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentArticleBinding
    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_article,
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
        binding.apply {
            btnClose.setOnClickListener {
                dismiss()
            }

            btnShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, binding.article?.url)
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share with"))
            }

            btnReadFullStory.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(binding.article?.url)
                    )
                )
            }

            btnSave.setOnClickListener {
                if (Article.bookmarkList.contains(binding.article ?: Article())) {
                    Article.bookmarkList.remove(binding.article ?: Article())
                    btnSave.setImageResource(R.drawable.save)
                } else {
                    Article.bookmarkList.add(binding.article ?: Article())
                    btnSave.setImageResource(R.drawable.bookmark_fill)
                }
            }
        }

        parentFragmentManager.setFragmentResultListener(
            "article",
            viewLifecycleOwner
        ) { _, bundle ->
            val res = bundle.getParcelable<Article>("article")
            Log.d("res", "initViews: $res")
            binding.apply {
                article = res
            }
        }

//        dataViewModel.dataList.observe(viewLifecycleOwner) { dataList ->
//            binding.apply {
//                article = dataViewModel
//            }
//            Log.d("Res", "initViews: $dataList")
//        }

    }

}