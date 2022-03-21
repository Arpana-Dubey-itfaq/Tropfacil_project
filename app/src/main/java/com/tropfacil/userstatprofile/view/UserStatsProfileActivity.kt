package com.tropfacil.userstatprofile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tropfacil.R
import com.tropfacil.badge.adapter.MybadgeAdapter

import com.tropfacil.databinding.ActivityUserstatsProfileBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter

class UserStatsProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserstatsProfileBinding
    lateinit var homeCourseListAdapter: HomeCourseListAdapter
    lateinit var homeAdapter: HomeAdapter
    lateinit var mybadgeAdapter: MybadgeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserstatsProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()

    }

    fun setonClickListner() {
        binding.incTopbar.imgBack.setOnClickListener {
            finish()
        }


    }

    fun setData() {
        binding.incTopbar.itemHeader.text = getString(R.string.earn_budges)
        homeCourseListAdapter = HomeCourseListAdapter()
        binding.relrecentCourse.adapter = homeCourseListAdapter
        homeAdapter = HomeAdapter()
        binding.relInProgress.adapter = homeAdapter
        mybadgeAdapter = MybadgeAdapter()
        binding.itemBadge.relCourse.adapter = mybadgeAdapter
        binding.incTopbar.itemHeader.text=getString(R.string.profile_statistics)
        binding.itemBadge.tvTitle.text=getString(R.string.my_badge)


    }


}