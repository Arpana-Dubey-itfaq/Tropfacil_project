package com.tropfacil

import android.os.Bundle
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityCompletedcourseBinding
import com.tropfacil.databinding.ActivityCongratulationsBadgeEarnBinding

class CongratulationsEarnBadgeActivity : BaseActivity() {
    lateinit var binding: ActivityCongratulationsBadgeEarnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationsBadgeEarnBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}