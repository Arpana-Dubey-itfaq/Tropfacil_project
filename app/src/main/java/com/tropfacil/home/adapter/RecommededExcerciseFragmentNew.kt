package com.tropfacil.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.app.leust.data.Data
import com.tropfacil.base.BaseFragment
import com.tropfacil.data.Theme
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.home.view.HomeViewModel
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class RecommededExcerciseFragmentNew(val theme: Theme) : BaseFragment() {
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
        homeAdapter = HomeAdapternew(requireContext(),theme.parcours)
        binding.relCourse.adapter = homeAdapter
    }

    private fun initObserver () {


        Data.token = PreferenceProvider(requireContext()).getUserToken()
        Data.header = "v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo"


        homeViewModel.HomeData(Data.header, Data.token)
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
    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            homeViewModel._syncItemsStateFlow.collect {
                    homeresponse ->
                when (homeresponse) {
                    is SafeApiCall.Loading -> {
                        // binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        // binding.progressBar.isVisible = false
                        //showErrorMsg(it.exception)
                    }
                    is SafeApiCall.Successhome -> {
                        // binding.progressBar.isVisible = false
                        homeViewModel._syncItemsStateFlow.value
                        // setData(homeresponse.data as home_response)
                        // loadBannersList(homeresponse.data as home_response)

                        //viewModel.syncGuestItems(getUUID())
                    }
                    else -> {
                        val  s=""
                    }
                }
            }
        }


    }

}