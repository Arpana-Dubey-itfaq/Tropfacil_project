package com.tropfacil.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.leust.data.Data.Companion.header
import com.app.leust.data.Data.Companion.token
import com.example.example.Homeresponse
import com.tropfacil.Dashboard
import com.tropfacil.base.BaseActivity
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider

import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import com.tropfacil.message.view.MessageActivity
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.ui.allusertypes.auth.login.LoginFragmentDirections
import com.tropfacil.ui.allusertypes.auth.login.LoginViewModel
import com.tropfacil.userstatprofile.view.UserStatsProfileActivity
import com.tropfacil.util.Constants
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(), HomeToCourseDetailsListener {
   // private val homeViewModel: HomeViewModel by viewModel()
    private val homeViewModel by inject<HomeViewModel>()
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

    fun setTabLayout() {

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
                     tabview.imgIcon.setImageResource(R.drawable.menu_home)

                    //  tabview.tvExerciseName.text = "hfgdsghf"
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
            binding.tabscheduleCourse.setTabMode(TabLayout.MODE_SCROLLABLE);

            when (position) {
                0 -> {
                    tab.text = "CSS3 - ENI® CERTIFICATIONS"
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    tab.text = "FORMATION PLATEFORME"
                }
                2 -> {
                    tab.text = "PACK OFFICE 2019 INTÉGRAL"
                }
                3 -> {
                    tab.text = "REFLEX'ENGLISH NIVEAU 1"
                }
                4 -> {
                    tab.text = "This Month"
                }
            }
        }.attach()
    }



    fun setData() {
        homeCourseAdapter = HomeCourseAdapter(this)
        binding.relCourse.adapter = homeCourseAdapter
        viewPagerExcerAdapter = ViewPagerAdapter(requireActivity(), 5)
        viewPagerSchudeleCourseAdapter = ViewPagerAdapter(requireActivity(), 5)

        binding.viewPagerExercise.adapter = viewPagerExcerAdapter
        binding.viewPagerscheduleCourse.adapter = viewPagerSchudeleCourseAdapter
        binding.incCountine.cardPlay.visibility = View.VISIBLE

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT=Constants.HomeFragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
        setTabLayout()
        initObserver()
        initObservers()
       // initObserver()
    }
    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            homeViewModel._syncItemsStateFlow.collect {
                    homeresponse ->
                when (homeresponse) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        //showErrorMsg(it.exception)
                    }
                    is SafeApiCall.Successhome -> {
                        binding.progressBar.isVisible = false
                        homeViewModel._syncItemsStateFlow.value
                        //viewModel.syncGuestItems(getUUID())
                    }
                    else -> {
val  s=""
                    }
                }
            }
        }


    }
    fun setListner() {
        binding.topbar.imgUser.setOnClickListener {
            homeOptionsListener.onClickMenu()
        }
        binding.topbar.imgsearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.topbar.imgNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))

        }
        binding.topbar.imgmessage.setOnClickListener {
            val writeAMessageFragment = WriteAMessageFragment()
            (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, writeAMessageFragment)

//            startActivity(Intent(requireContext(), MessageActivity::class.java))

        }
        binding.incLevelInfo.imgNext.setOnClickListener {
            startActivity(Intent(requireContext(), UserStatsProfileActivity::class.java))

        }
        binding.incCountine.card.setOnClickListener {
           // findNavController().navigate(HomeFragmentDirections.actionCourseperFragment())


        }
/*
        binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.view1.visibility = View.GONE

                Log.i(TAG, "Scroll DOWN")
            }
            if (scrollY < oldScrollY) {

                Log.i(TAG, "Scroll UP")
            }
            if (scrollY == 0) {
                binding.view1.visibility = View.VISIBLE
                Log.i(TAG, "TOP SCROLL")
            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                Log.i(TAG, "BOTTOM SCROLL")
            }
        })
*/
    }
    private fun initObserver () {


            token = PreferenceProvider(requireContext()).getUserToken()


        homeViewModel.HomeData(token)
        //findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

//    homeViewModel.allUsers.observe(viewLifecycleOwner, Observer { listUser ->
//      if (listUser.isNotEmpty()) {
//        listUser.forEach {
//          Timber.i(it.name)
//        }
//      }
//    })
       /* homeViewModel.vmGetUserList()
        homeViewModel.userList.observe(viewLifecycleOwner, Observer { listUser ->
            listUser.forEach {
            //    Timber.i(it.themes.indexOf(i))
            }
        })*/
    }

    override fun navigateToCourseDetailsScreen(courseId: Int) {
    }



}