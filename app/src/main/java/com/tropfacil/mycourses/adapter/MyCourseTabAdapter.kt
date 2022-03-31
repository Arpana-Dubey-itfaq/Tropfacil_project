package com.tropfacil.mycourses.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tropfacil.data.Theme
import com.tropfacil.mycourses.view.MyCourseInfoFragment
import com.tropfacil.ui.nav.exercices.ExerciseInfoFragment
import com.tropfacil.util.Constants

/**
 * Created by Nimesh Patel on 31-03-2022.
 */
class MyCourseTabAdapter(
    fm: FragmentManager,
    private val totalTabs: Int,
    private val themeList: List<Theme>,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = totalTabs

    override fun createFragment(position: Int): Fragment {
        val myCourseInfoFragment = MyCourseInfoFragment()
        val bundle: Bundle = Bundle()
        bundle.putSerializable(Constants.THEME_INFO_LIST, themeList[position])
        myCourseInfoFragment.arguments = bundle
        return myCourseInfoFragment
    }
}