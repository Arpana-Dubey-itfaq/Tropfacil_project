package com.tropfacil

import android.os.Bundle
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityCongratulationsBinding

class CompletedCourseActivity : BaseActivity() {
    lateinit var binding: ActivityCongratulationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}