package com.tropfacil.ui.nav.exercices.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tropfacil.model.exercices.ExercicesInfoList
import com.tropfacil.ui.nav.exercices.ExerciseInfoFragment
import com.tropfacil.util.Constants.Companion.EXERCICES_INFO_LIST

/**
 * Created by Nimesh Patel on 24-03-2022.
 */
class ExercicesTabAdapter(
    fm: FragmentManager,
    private val totalTabs: Int,
    private val exercicesList: List<ExercicesInfoList>,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = totalTabs

    override fun createFragment(position: Int): Fragment {
        val exerciseInfoFragment = ExerciseInfoFragment()
        val bundle: Bundle = Bundle()
        bundle.putSerializable(EXERCICES_INFO_LIST, exercicesList[position])
        exerciseInfoFragment.arguments = bundle
        return exerciseInfoFragment
    }


}