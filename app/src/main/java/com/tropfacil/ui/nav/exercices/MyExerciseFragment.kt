package com.tropfacil.ui.nav.exercices


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentExcerciseBinding
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.model.exercices.ExercicesInfoList
import com.tropfacil.model.exercices.ExercicesListResponse
import com.tropfacil.network.service.SafeApiResponse
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.ui.nav.exercices.adapter.ExercicesTabAdapter
import com.tropfacil.util.Constants
import com.tropfacil.util.interfaces.HomeOptionsListener
import kotlinx.android.synthetic.main.fragment_excercise.*
import kotlinx.android.synthetic.main.include_title_with_back.view.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class MyExerciseFragment : BaseFragment() {
    lateinit var binding: FragmentExcerciseBinding
    private val viewModel by inject<ExercicesViewModel>()
    lateinit var homeOptionsListener: HomeOptionsListener
//    lateinit var viewPagerExcerAdapter: ViewPagerAdapter
    private var exerciseList:List<ExercicesInfoList> = ArrayList()
    private var exercicesTabAdapter:ExercicesTabAdapter? =null
    companion object {
        const val TAG = "MyExerciseFragment"

        @JvmStatic
        fun newInstance() = MyExerciseFragment().apply {

        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
        binding.topbar.tvHeading.text = getString(R.string.str_my_exercices)


        if(exerciseList.isNotEmpty()){
            exerciseList.forEach {
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.libelle))
            }
            exercicesTabAdapter = ExercicesTabAdapter(
                childFragmentManager,
                binding.tabLayout.tabCount,
                exerciseList,
                lifecycle
            )
            viewPager.adapter = exercicesTabAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYEXERCISEFragment

        binding = FragmentExcerciseBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
        initObservers()
    }
    private fun initObservers() {
        viewModel.getExerciseList()
        lifecycleScope.launchWhenStarted {
            viewModel._getExercicesStateFlow.collect { it ->
                when (it) {
                    is SafeApiResponse.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiResponse.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception.toString())
                    }
                    is SafeApiResponse.Success -> {
                        binding.progressBar.isVisible = false
                            updateData(it.data)
                    }
                    else -> {
                    }
                }
            }
        }

    }

    private fun updateData(exercicesListResponse: ExercicesListResponse?) {
        if(exercicesListResponse!=null && exercicesListResponse.exercices.isNotEmpty() ) {
            exerciseList = exercicesListResponse.exercices
            setData()
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

        }

    }
}