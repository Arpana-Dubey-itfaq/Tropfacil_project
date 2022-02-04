package com.tropfacil.notificaions.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.databinding.ActivityNotificationsBinding
import com.tropfacil.databinding.ActivitySearchBinding
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.notificaions.adapter.NotificationListAdapter

class NotificationsActivity : BaseActivity() {

    lateinit var binding: ActivityNotificationsBinding
    lateinit var adapter: NotificationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()

    }

    fun setonClickListner() {
        binding.incTopbar.imgBack.setOnClickListener {
            finish()
        }


    }

    fun setData() {
        binding.incTopbar.itemHeader.text=getString(R.string.notifiation)
        adapter = NotificationListAdapter()
        binding.relNotifications.adapter = adapter

    }


}