package com.simform.news.ui.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.simform.news.R
import com.simform.news.databinding.ActivityHomeBinding
import com.simform.news.ui.home.viewmodel.HomeVM


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        // Set navigation for fragment
        val navHost = supportFragmentManager.findFragmentById(R.id.fmContainer) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHost.navController)

        viewModel = ViewModelProvider(this).get(HomeVM::class.java)

    }

}