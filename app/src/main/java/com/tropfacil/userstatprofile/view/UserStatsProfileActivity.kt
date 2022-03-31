package com.tropfacil.userstatprofile.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tropfacil.R
import com.tropfacil.userstatprofile.adapter.MybadgeAdapter
import com.tropfacil.base.BaseActivity
import com.tropfacil.data.provider.PREF_USER_FIRST_NAME
import com.tropfacil.data.provider.PREF_USER_LAST_NAME
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.ActivityUserstatsProfileBinding
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.ui.nav.home.userstats.badges.EarnBadgesFragment
import com.tropfacil.ui.nav.profile.ProfileSettingsViewModel
import com.tropfacil.userstatprofile.adapter.MyActivitiesAdapter
import com.tropfacil.userstatprofile.adapter.MyAvancementGlobalAdapter
import kotlinx.coroutines.flow.collect
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject

class UserStatsProfileActivity : BaseActivity() {
    lateinit var binding: ActivityUserstatsProfileBinding
    lateinit var mybadgeAdapter: MybadgeAdapter
    private val preferenceProvider by inject<PreferenceProvider>()
    private val viewModel by inject<ProfileSettingsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserstatsProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()
        initObservers()
    }


    fun setonClickListner() {
        binding.incTopbar.imgBack.setOnClickListener {
            finish()

        }
        binding.linFormation.setOnClickListener {
            binding.viewFormations.isVisible = true
            binding.viewEvaluations.visibility = View.INVISIBLE

            val activitiesAdapter = MyActivitiesAdapter(true)
            binding.rvActivities.adapter = activitiesAdapter

        }
        binding.linEvaluations.setOnClickListener {
            binding.viewEvaluations.isVisible = true
            binding.viewFormations.visibility = View.INVISIBLE

            val activitiesAdapter = MyActivitiesAdapter(false)
            binding.rvActivities.adapter = activitiesAdapter

        }
        /*     binding.itemBadge.tvViewAll.setOnClickListener {
                 navigateToEarnBadgeScreen()
             }
             binding.tvChange.setOnClickListener {
                 binding.fragmentContainer.isVisible = true
                 val earnBadgesFragment = ProfileFragment()
                 (this as BaseActivity).visitNewFragment(R.id.fragment_container, earnBadgesFragment)

             }

        */

        val lastname = preferenceProvider.getString(PREF_USER_LAST_NAME, "")
        val name = preferenceProvider.getString(PREF_USER_FIRST_NAME, "")
        binding.tvUsername.text = "${name} $lastname"


    }

    private fun navigateToEarnBadgeScreen() {
        binding.fragmentContainer.isVisible = true
        val earnBadgesFragment = EarnBadgesFragment()
        (this as BaseActivity).visitNewFragment(R.id.fragment_container, earnBadgesFragment)
    }

    fun setData() {
        val activitiesAdapter = MyActivitiesAdapter(true)
        binding.rvActivities.adapter = activitiesAdapter

//        binding.incTopbar.itemHeader.text = getString(R.string.earn_budges)
        val avancementAdapter = MyAvancementGlobalAdapter()
        binding.rvAvancementGlobal.adapter = avancementAdapter

        mybadgeAdapter = MybadgeAdapter()
        binding.itemBadge.relCourse.adapter = mybadgeAdapter
        binding.incTopbar.itemHeader.text = getString(R.string.profile_statistics)
        binding.itemBadge.tvTitle.text = getString(R.string.my_badge)


    }

    private fun initObservers() {
        viewModel.getProfilePicture()
        lifecycleScope.launchWhenStarted {
            viewModel._profilePictureStateFlow.collect { it ->
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception.toString())
                    }
                    is SafeApiCall.SuccessResponseBody -> {
                        binding.progressBar.isVisible = false
                        displayImages(it.data)
                    }
                    else -> {
                    }
                }
            }
        }

    }

    private fun displayImages(data: ResponseBody) {
        try {
            val bitmap: Bitmap = BitmapFactory.decodeStream(data.byteStream())
            Glide.with(this)
                .asBitmap()
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_profile)
                .into(binding.imgUser)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}