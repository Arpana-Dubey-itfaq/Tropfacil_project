package com.tropfacil.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.base.BaseFragment
import com.tropfacil.data.Theme
import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.home.view.HomeViewModel
import org.koin.android.ext.android.inject

class RecommededExcerciseFragmentNew(val theme: Theme) : BaseFragment() {
    lateinit var binding: FragmentTabRecommededExerciseBinding
    lateinit var homeAdapter: HomeAdapternew

    private val homeViewModel by inject<HomeViewModel>()
    companion object {
        const val TAG = "RecommededExcerciseFragment"


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
        if(!theme.parcours.isEmpty()) {
            homeAdapter = HomeAdapternew(requireContext(), theme,true)
            binding.relCourse.adapter = homeAdapter
        }else{
            homeAdapter = HomeAdapternew(requireContext(), theme,false)
            binding.relCourse.adapter = homeAdapter
        }
    }



}