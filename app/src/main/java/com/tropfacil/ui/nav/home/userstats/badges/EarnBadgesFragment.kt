package com.tropfacil.ui.nav.home.userstats.badges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentEarnBadgesBinding
import com.tropfacil.databinding.FragmentProfileBinding
import com.tropfacil.model.badges.BadgeListResponse
import com.tropfacil.network.BaseResponse
import com.tropfacil.network.service.SafeApiResponse
import com.tropfacil.textCapSentences
import com.tropfacil.ui.nav.exercices.ExercicesViewModel
import com.tropfacil.ui.nav.home.userstats.badges.adapter.EarnBadgeAdapter
import com.tropfacil.userstatprofile.UserStatsProfileViewModel
import com.tropfacil.utils.ItemClickListener
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class EarnBadgesFragment : BaseFragment() {
    lateinit var binding: FragmentEarnBadgesBinding
    private val viewModel by inject<UserStatsProfileViewModel>()
    private var earnBadgeAdapter:EarnBadgeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEarnBadgesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SetUI()
        initObservers()
    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text =
            textCapSentences(getString(R.string.lbl_earn_badges))
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        earnBadgeAdapter = EarnBadgeAdapter(requireContext(),object :ItemClickListener{
            override fun onItemClick(bundle: Bundle?) {

            }
        })
        binding.recyclerView.adapter = earnBadgeAdapter
    }
    private fun initObservers() {
        viewModel.getBadges()
        lifecycleScope.launchWhenStarted {
            viewModel._getBadgesStateFlow.collect { it ->
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

    private fun updateData(badgeListResponse: BadgeListResponse) {
        binding.recyclerView.isVisible = badgeListResponse.badges.isNotEmpty()
        binding.tvNoDataFound.isVisible = badgeListResponse.badges.isEmpty()
    }

}