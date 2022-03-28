package com.tropfacil.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme
import com.tropfacil.data.Theme
import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.home.view.HomeViewModel
import com.tropfacil.mycourses.view.CourseDetailsFragment
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener
import org.koin.android.ext.android.inject

class RecommededExcerciseFragmentNew(val theme: Theme) : BaseFragment(), HomeToCourseDetailsListener, ResumeFragmentListener {
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
            homeAdapter = HomeAdapternew(requireContext(), theme,true,this)
            binding.relCourse.adapter = homeAdapter
        }else{
            homeAdapter = HomeAdapternew(requireContext(), theme,false,this)
            binding.relCourse.adapter = homeAdapter
        }
    }

    override fun navigateToCourseDetailsScreenViaParCour(parcourItem: Parcour) {
        (requireActivity() as BaseActivity).updateResumeFragment(this)
        val courseDetailsFragment = CourseDetailsFragment.newInstance(parcourItem, true)
        (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, courseDetailsFragment)

    }

    override fun navigateToCourseDetailsScreenViaSousTheme(sousItem: Soustheme) {
        (requireActivity() as BaseActivity).updateResumeFragment(this)
        val courseDetailsFragment = CourseDetailsFragment.newInstance(sousItem,false)
        (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, courseDetailsFragment)

    }

    override fun onFragmentResume(bundle: Bundle?) {
        //nothing to do here
    }


}