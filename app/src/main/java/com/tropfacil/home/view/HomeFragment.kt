package com.tropfacil.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding


class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: HomeCourseAdapter
    lateinit var viewPagerExcerAdapter: ViewPagerAdapter
    lateinit var viewPagerSchudeleCourseAdapter: ViewPagerAdapter

    companion object {
        const val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance() = HomeFragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }

    fun setData() {
        homeCourseAdapter = HomeCourseAdapter()
        binding.relCourse.adapter = homeCourseAdapter
        viewPagerExcerAdapter = ViewPagerAdapter(requireActivity(),5)
        viewPagerSchudeleCourseAdapter = ViewPagerAdapter(requireActivity(),3)

        binding.viewPagerExercise.adapter = viewPagerExcerAdapter
        binding.viewPagerscheduleCourse.adapter = viewPagerSchudeleCourseAdapter

        TabLayoutMediator(binding.tabLayoutExercise, binding.viewPagerExercise) { tab, position ->
            /* val tabView = LayoutInflater.from(this.context)
                 .inflate(R.layout.custom_tab_recommeded_exercise, binding.tabLayoutExercise, false)
*/
            // tab.setCustomView(R.layout.custom_tab_recommeded_exercise);
            var tabview = CustomTabRecommededExerciseBinding.inflate(
                layoutInflater,
                binding.tabLayoutExercise,
                false
            )
            tab.setCustomView(tabview.root)

            when (position) {
                0 -> {
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    //   tabview.tvExerciseName.text = "hfgdsghf"

                    // you can set your tab text and color here for tab2
                }
                2 -> {
                    // tabview.tvExerciseName.text = "hfgdsghf"

                    // you can set your tab text and color here for tab3
                }
            }
        }.attach()

        TabLayoutMediator(
            binding.tabscheduleCourse,
            binding.viewPagerscheduleCourse
        ) { tab, position ->
            /* val tabView = LayoutInflater.from(this.context)
                 .inflate(R.layout.custom_tab_recommeded_exercise, binding.tabLayoutExercise, false)
*/
            // tab.setCustomView(R.layout.custom_tab_recommeded_exercise);


            when (position) {
                0 -> {
                    tab.text = "Today"
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    tab.text = "This week"
                }
                2 -> {
                    tab.text = "This Month"
                }
            }
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
    }

    fun setListner() {
        binding.topbar.imgUser.setOnClickListener {
            homeOptionsListener.onClickMenu()
        }
    }
}