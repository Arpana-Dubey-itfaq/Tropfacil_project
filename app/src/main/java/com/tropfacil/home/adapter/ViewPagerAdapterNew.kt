package com.tropfacil.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tropfacil.data.Theme
import com.tropfacil.home.view.RecommededExcerciseFragment

class ViewPagerAdapterNew(fa: FragmentActivity,private val listOfTitle: List<Theme>) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int = listOfTitle.size

    override fun createFragment(position: Int): Fragment {
        return RecommededExcerciseFragmentNew(listOfTitle[position])
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}