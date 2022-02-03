package com.tropfacil

import android.os.Bundle
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityCompletedcourseBinding

class CompletedCourseActivity : BaseActivity() {
    lateinit var binding: ActivityCompletedcourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompletedcourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}