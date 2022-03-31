package com.tropfacil.badge.view

import android.os.Bundle
import com.tropfacil.R
import com.tropfacil.userstatprofile.adapter.MybadgeAdapter
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.*

class BadgeActivity : BaseActivity() {

    lateinit var binding: ActivityBadgeBinding
    lateinit var adapter: MybadgeAdapter

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
        adapter = MybadgeAdapter()
        binding.relBadge.adapter = adapter

    }


}