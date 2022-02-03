package com.tropfacil.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tropfacil.CompletedCourseActivity
import com.tropfacil.R
import com.tropfacil.RateReviewActivity
import com.tropfacil.badge.view.BadgeActivity
import com.tropfacil.base.BaseActivity
import com.tropfacil.category.view.AllCateogiesFragment
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.home.view.Home1Fragment
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.mycourses.view.MyCourseFragment
import com.tropfacil.myexcersise.view.MyExerciseFragment
import com.tropfacil.util.interfaces.HomeOptionsListener

class MainActivity : BaseActivity(), HomeOptionsListener {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToHome1Screen()
      //  startActivity()
    }

    fun startActivity() {
        startActivity(Intent(this, BadgeActivity::class.java))
    }

    override fun onClickMenu() {
        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun lockDrawer(lock: Boolean) {
        if (lock) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }


    override fun navigateToHomeScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment.newInstance(), HomeFragment.TAG).commit()

    }

    fun navigateToMyCoursesScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MyCourseFragment.newInstance(), MyCourseFragment.TAG)
            .commit()

    }

    fun navigateToCategoryScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AllCateogiesFragment.newInstance(), AllCateogiesFragment.TAG)
            .commit()

    }
    fun navigateToHome1Screen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Home1Fragment.newInstance(), Home1Fragment.TAG)
            .commit()

    }
    fun navigateToMyExerciseScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MyExerciseFragment.newInstance(),
                MyExerciseFragment.TAG
            ).commit()

    }

}