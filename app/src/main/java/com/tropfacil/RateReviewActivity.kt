package com.tropfacil

import android.content.Intent
import android.os.Bundle
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityCongratulationsBinding
import com.tropfacil.databinding.ActivityRateBinding
import com.tropfacil.main.view.MainActivity

class RateReviewActivity : BaseActivity() {
    lateinit var binding: ActivityRateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListner()

    }

    fun setListner() {
        binding.cardSubmit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}