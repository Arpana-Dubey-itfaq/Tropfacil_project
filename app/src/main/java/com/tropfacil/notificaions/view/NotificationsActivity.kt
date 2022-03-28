package com.tropfacil.notificaions.view

import android.os.Bundle
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityNotificationsBinding
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