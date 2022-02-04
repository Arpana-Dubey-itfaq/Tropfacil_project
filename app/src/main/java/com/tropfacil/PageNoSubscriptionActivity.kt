package com.tropfacil

import android.os.Bundle
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityCompletedcourseBinding
import com.tropfacil.databinding.ActivityCongratulationsBadgeEarnBinding
import com.tropfacil.databinding.ActivityPageNoSubscriptionBinding

class PageNoSubscriptionActivity : BaseActivity() {
    lateinit var binding: ActivityPageNoSubscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageNoSubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incTopbar.itemHeader.text = "Page name"
        setListner()

    }

    fun setListner() {
        binding.incTopbar.imgBack.setOnClickListener {
            finish()
        }
    }
}