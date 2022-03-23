package com.tropfacil.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.app.leust.data.Data

import com.tropfacil.base.BaseFragment
import com.tropfacil.data.Theme
import com.tropfacil.data.provider.PreferenceProvider

import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeAdapternew
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class RecommededExcerciseFragment() : BaseFragment() {
    lateinit var binding: FragmentTabRecommededExerciseBinding
    lateinit var homeAdapter: HomeAdapter

    private val homeViewModel by inject<HomeViewModel>()
    companion object {
        const val TAG = "RecommededExcerciseFragment"

        @JvmStatic
        fun newInstance() = RecommededExcerciseFragment().apply {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabRecommededExerciseBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
setData()
        /*initObserver()
        initObservers()*/
    }

    fun setData() {
        homeAdapter = HomeAdapter()
        binding.relCourse.adapter = homeAdapter
    }


}