package com.tropfacil.mycourses.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.app.leust.data.Data
import com.google.android.material.tabs.TabLayout
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.data.Theme
import com.tropfacil.data.home_response
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.FragmentMyCourseBinding
import com.tropfacil.home.view.HomeViewModel
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.mycourses.adapter.MyCourseTabAdapter
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.ui.nav.exercices.adapter.ExercicesTabAdapter
import com.tropfacil.util.Constants
import com.tropfacil.util.interfaces.HomeOptionsListener
import kotlinx.android.synthetic.main.fragment_excercise.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class MyCourseFragment : BaseFragment() {
    lateinit var binding: FragmentMyCourseBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    private val homeViewModel by inject<HomeViewModel>()
    private val preferenceProvider by inject<PreferenceProvider>()
    private var themeList: List<Theme> = ArrayList()
    private  var myCourseTabAdapter:MyCourseTabAdapter? = null
    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance() = MyCourseFragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Constants.FRAGMENT = Constants.MYCOURSESFragment

        binding = FragmentMyCourseBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
        initObserver()
    }
    private fun initObserver() {
        Data.token = preferenceProvider.getUserToken()
        if (Data.token != null)
            homeViewModel.HomeData(Data.token)

        lifecycleScope.launchWhenStarted {
            homeViewModel._syncItemsStateFlow.collect { homeresponse ->
                when (homeresponse) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(homeresponse.exception)
                    }
                    is SafeApiCall.Successhome -> {
                        binding.progressBar.isVisible = false
                        homeViewModel._syncItemsStateFlow.value
                        updateData(homeresponse.data)

                    }
                    else -> {
                        val s = ""
                    }
                }
            }
        }

    }

    private fun updateData(homeResponse: home_response?) {

            themeList = homeResponse?.themes?: ArrayList()
            if (themeList.isNotEmpty()) {
                themeList.forEach {
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.libelle))
                }
                myCourseTabAdapter = MyCourseTabAdapter(
                    childFragmentManager,
                    binding.tabLayout.tabCount,
                    themeList,
                    lifecycle
                )
                viewPager.adapter = myCourseTabAdapter

            }

        binding.viewPager.isUserInputEnabled = false

        val myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.getTabAt(position)?.select()
            }
        }
        binding.viewPager.registerOnPageChangeCallback(myPageChangeCallback)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //Close Child Fragment if it has
                val fragmentName = requireActivity().supportFragmentManager.findFragmentById(R.id.fl_child_container)?.tag
                if(fragmentName != null)
                    requireActivity().supportFragmentManager.popBackStack()

                viewPager.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    fun setData() {

        binding.topbar.tvHeading.text = getString(R.string.str_my_courses)

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
            (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container,
                writeAMessageFragment)


        }
    }


}