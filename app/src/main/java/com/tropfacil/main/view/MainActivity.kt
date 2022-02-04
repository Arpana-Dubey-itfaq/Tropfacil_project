package com.tropfacil.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
        navigateToHomeScreen()
        binding.navigationView.setNavigationItemSelectedListener {
            it.menuInfo

            when (it.itemId) {
                R.id.navHome -> Toast.makeText(

                applicationContext,
                    "Clicked Home",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.mycource -> Toast.makeText(
                    applicationContext,
                    "Clicked My Course",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.myexcersises -> Toast.makeText(
                    applicationContext,
                    "Clicked My Excersises",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.recomended -> Toast.makeText(
                    applicationContext,
                    "Clicked Recomended",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.allcatagories -> Toast.makeText(
                    applicationContext,
                    "Clicked All Catagories",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.profile -> Toast.makeText(
                    applicationContext,
                    "Clicked Profile",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.favourties -> Toast.makeText(
                    applicationContext,
                    "Clicked Favorities",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.accountsettings -> Toast.makeText(
                    applicationContext,
                    "Clicked Account Settings",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.needhelp -> Toast.makeText(
                    applicationContext,
                    "Clicked Need Help",
                    Toast.LENGTH_SHORT
                ).show()

            }
            true


        }

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