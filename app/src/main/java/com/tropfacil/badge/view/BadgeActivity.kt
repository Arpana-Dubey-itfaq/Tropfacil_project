package com.tropfacil.badge.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.badge.adapter.BadgeListAdapter
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.*
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.notificaions.adapter.NotificationListAdapter

class BadgeActivity : BaseActivity() {

    lateinit var binding: ActivityBadgeBinding
    lateinit var adapter: BadgeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBadgeBinding.inflate(layoutInflater)
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
        binding.incTopbar.itemHeader.text = getString(R.string.earn_budges)
        adapter = BadgeListAdapter()
        binding.relBadge.adapter = adapter

    }


}