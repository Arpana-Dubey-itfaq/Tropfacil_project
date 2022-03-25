package com.tropfacil.userstatprofile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.tropfacil.R
import com.tropfacil.badge.adapter.MybadgeAdapter
import com.tropfacil.base.BaseActivity

import com.tropfacil.databinding.ActivityUserstatsProfileBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter
import com.tropfacil.ui.nav.account.email.UpdateEmailFragment
import com.tropfacil.ui.nav.home.userstats.badges.EarnBadgesFragment
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener

class UserStatsProfileActivity : BaseActivity(), HomeToCourseDetailsListener {
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
        binding.itemBadge.tvViewAll.setOnClickListener {
            navigateToEarnBadgeScreen()
        }


    }
    private fun navigateToEarnBadgeScreen() {
        binding.fragmentContainer.isVisible = true
        val earnBadgesFragment = EarnBadgesFragment()
        (this as BaseActivity).visitNewFragment(R.id.fragment_container, earnBadgesFragment)
    }

    fun setData() {
        binding.incTopbar.itemHeader.text = getString(R.string.earn_budges)
        homeCourseListAdapter = HomeCourseListAdapter(this)
        binding.relrecentCourse.adapter = homeCourseListAdapter
        homeAdapter = HomeAdapter()
        binding.relInProgress.adapter = homeAdapter
        mybadgeAdapter = MybadgeAdapter()
        binding.itemBadge.relCourse.adapter = mybadgeAdapter
        binding.incTopbar.itemHeader.text=getString(R.string.profile_statistics)
        binding.itemBadge.tvTitle.text=getString(R.string.my_badge)


    }

    override fun navigateToCourseDetailsScreen(courseId: Int) {

    }


}