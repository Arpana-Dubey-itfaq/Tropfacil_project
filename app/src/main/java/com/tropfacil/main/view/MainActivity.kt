package com.tropfacil.main.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tropfacil.R
import com.tropfacil.badge.view.BadgeActivity
import com.tropfacil.base.BaseActivity
import com.tropfacil.category.view.AllCateogiesFragment
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.databinding.CoursePerChapterBinding
import com.tropfacil.home.view.Home1Fragment
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.mycourses.view.Course_chapter_detail_Fragment
import com.tropfacil.mycourses.view.Course_per_chapter_Fragment
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
                R.id.navHome ->
                    navigateToHomeScreen()
               // updateBottomNavBarColor(R.color.green)

                R.id.mycource ->
                    navigateToMyCoursesScreen()
                R.id.myexcersises ->
                    navigateToMyExerciseScreen()
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

    private val bottomNavBarStateList =
        arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked))

    private fun updateBottomNavBarColor(currentSelectedColor: Int) {
        val colorList = intArrayOf(
            ContextCompat.getColor(this, currentSelectedColor),
            ContextCompat.getColor(this, R.color.red)
        )
        val colorStateList = ColorStateList(
            bottomNavBarStateList,
            colorList
        )
        binding.navigationView.itemIconTintList = colorStateList
        binding.navigationView.itemTextColor = colorStateList
    }



    override fun navigateToHomeScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                HomeFragment.newInstance(),
                HomeFragment.TAG
            ).commit()

    }

    fun navigateToMyCoursesScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MyCourseFragment.newInstance(), MyCourseFragment.TAG)
            .commit()

    }

    fun navigateToCategoryScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                AllCateogiesFragment.newInstance(),
                AllCateogiesFragment.TAG
            )
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