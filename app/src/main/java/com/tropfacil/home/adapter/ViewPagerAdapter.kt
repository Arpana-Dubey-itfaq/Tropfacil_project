package com.tropfacil.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tropfacil.home.view.RecommededExcerciseFragment

class ViewPagerAdapter(fa: FragmentActivity, size: Int) : FragmentStateAdapter(fa) {

    var viewPagersize: Int

    init {
        viewPagersize = size
    }

    override fun getItemCount() = viewPagersize

    override fun createFragment(position: Int): Fragment {
        return RecommededExcerciseFragment()
    }
}