package com.tropfacil.userstatprofile.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.badge.adapter.BadgeListAdapter
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.*
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.notificaions.adapter.NotificationListAdapter
import com.tropfacil.userstatprofile.adapter.MybadgeAdapter

class UserStatsProfileActivity : BaseActivity() {

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


    }


}