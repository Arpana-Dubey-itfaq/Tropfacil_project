package com.tropfacil.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tropfacil.base.BaseFragment

import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseAdapter

class RecommededExcerciseFragment : BaseFragment() {
    lateinit var binding: FragmentTabRecommededExerciseBinding
    lateinit var homeAdapter: HomeAdapter

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
    }

    fun setData() {
        homeAdapter = HomeAdapter()
        binding.relCourse.adapter = homeAdapter
    }
}