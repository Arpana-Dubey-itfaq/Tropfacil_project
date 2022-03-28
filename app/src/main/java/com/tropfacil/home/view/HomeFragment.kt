package com.tropfacil.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import androidx.lifecycle.lifecycleScope
import com.app.leust.data.Data.Companion.header
import com.app.leust.data.Data.Companion.nom
import com.app.leust.data.Data.Companion.pernom
import com.app.leust.data.Data.Companion.token
import com.tropfacil.data.home_response
import com.tropfacil.base.BaseActivity
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.home.adapter.*

import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.userstatprofile.view.UserStatsProfileActivity
import com.tropfacil.util.Constants
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class HomeFragment : BaseFragment(), HomeToCourseDetailsListener {
   // private val homeViewModel: HomeViewModel by viewModel()
    private val homeViewModel by inject<HomeViewModel>()
    lateinit var binding: FragmentHomeBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: HomeCourseAdapter
    lateinit var viewPagerExcerAdapter: ViewPagerAdapterEvery
    lateinit var viewPagerSchudeleCourseAdapter: ViewPagerAdapterNew


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

    fun setTabLayout(bannersResponse: home_response) {

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
//tab.position
            tab.text=bannersResponse.themes[position].libelle
           /* for (i in 0 until bannersResponse.themes?.size!!) {

                tab.text=bannersResponse.themes[i].libelle
            }*/
        }.attach()
    }



    fun setData(bannersResponse: home_response) {
        homeCourseAdapter = HomeCourseAdapter(this)
        binding.relCourse.adapter = homeCourseAdapter
        viewPagerExcerAdapter = ViewPagerAdapterEvery(requireActivity(), 5)
        viewPagerSchudeleCourseAdapter = ViewPagerAdapterNew(requireActivity(),bannersResponse.themes)

        binding.viewPagerExercise.adapter = viewPagerExcerAdapter
        binding.viewPagerscheduleCourse.adapter = viewPagerSchudeleCourseAdapter
        binding.incCountine.cardPlay.visibility = View.VISIBLE
        nom=PreferenceProvider(requireContext()).getNom()
        pernom=PreferenceProvider(requireContext()).getperNom()
       // binding.incLevelInfo.tvcourse1.setText(nom)
        binding.incLevelInfo.tvcoursename.setText(nom+" "+pernom)


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

                        loadBannersList(homeresponse.data as home_response)

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
         //   (requireActivity() as BaseActivity).updateResumeFragment(this)
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

    private fun loadBannersList(bannersResponse: home_response) {
            /*val bannersAdapter =
                BannersAdapter(requireContext(), bannersResponse.response, true, this)
            binding.viewPagerBanner.adapter = bannersAdapter
//            binding.indicator.isVisible = true
            binding.indicator.count = binding.viewPagerBanner.indicatorCount
            binding.viewPagerBanner.onIndicatorProgress = { selectingPosition, progress ->
                binding.indicator.setProgress(selectingPosition, progress)
*/
      //  viewPagerSchudeleCourseAdapter = ViewPagerAdapter(requireActivity(), 5)



        setData(bannersResponse)
       // homeViewModel.parcorselist.value=bannersResponse.
        setTabLayout(bannersResponse)


    }


    override fun navigateToCourseDetailsScreen(courseId: Int) {
    }



}