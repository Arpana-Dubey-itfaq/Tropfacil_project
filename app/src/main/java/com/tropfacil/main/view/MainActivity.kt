package com.tropfacil.main.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tropfacil.R
import com.tropfacil.badge.view.BadgeActivity
import com.tropfacil.base.BaseActivity
import com.tropfacil.category.view.AllCateogiesFragment
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.home.view.Home1Fragment
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.mycourses.view.Course_chapter_Fragment
import com.tropfacil.mycourses.view.MyCourseFragment
import com.tropfacil.ui.nav.exercices.MyExerciseFragment
import com.tropfacil.ui.nav.account.AccountSettingsFragment
import com.tropfacil.ui.nav.profile.ProfileFragment
import com.tropfacil.util.interfaces.HomeOptionsListener
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : BaseActivity(), HomeOptionsListener {
    lateinit var binding: ActivityHomeBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToHomeScreen()
        homeOptionsListener = this
        initNavigationDrawer()
        binding.navigationView.userprofile
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

    private fun closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
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
            .replace(
                R.id.fragment_container,
                HomeFragment.newInstance(),
                HomeFragment.TAG
            ).commit()

    }

    override fun navigateToMyCoursesScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MyCourseFragment.newInstance(),
                MyCourseFragment.TAG
            )
            .commit()
    }

    override fun navigateToMyExerciseScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MyExerciseFragment.newInstance(),
                MyExerciseFragment.TAG
            ).commit()
    }

    override fun navigateToCategoryScreen() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                AllCateogiesFragment.newInstance(),
                AllCateogiesFragment.TAG
            )
            .commit()
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

    fun initNavigationDrawer() {

        // refer the navigation view of the xml
        //   val navigationView = findViewById(R.id.navigation_view) as NavigationView
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.navHome -> {
                    navigateToHomeScreen()
                    /*  Toast.makeText(applicationContext, "You Clicked Options A", Toast.LENGTH_SHORT)
                          .show()*/
                    binding.drawerLayout!!.closeDrawers()
                }
                R.id.mycource -> {
                    navigateToMyCoursesScreen()
                    /*   Toast.makeText(applicationContext, "You Clicked Options B", Toast.LENGTH_SHORT)
                           .show()*/
                    binding.drawerLayout!!.closeDrawers()
                }
                R.id.myexcersises -> {
                    navigateToMyExerciseScreen()
                    /*Toast.makeText(applicationContext, "You Clicked Options C", Toast.LENGTH_SHORT)
                        .show()*/
                    binding.drawerLayout!!.closeDrawers()
                }
                R.id.allcatagories -> {
                    navigateToCategoryScreen()
                    /*  Toast.makeText(applicationContext, "You Clicked Options C", Toast.LENGTH_SHORT)
                          .show()*/
                    binding.drawerLayout!!.closeDrawers()
                }
                R.id.profile -> {
                    navigateToProfileScreen()
                    binding.drawerLayout!!.closeDrawers()
                }
                R.id.accountsettings -> {
                    navigateToAccountSettingScreen()
                    binding.drawerLayout!!.closeDrawers()
                }
            }
            true
        }






        fun navigateToHome1Screen() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Home1Fragment.newInstance(), Home1Fragment.TAG)
                .commit()

        }


    }

    private fun navigateToProfileScreen() {
        val profileFragment = ProfileFragment()
        (this as BaseActivity).visitNewFragment(R.id.fragment_container, profileFragment)
    }
    private fun navigateToAccountSettingScreen() {
        val accountSettingsFragment = AccountSettingsFragment()
        (this as BaseActivity).visitNewFragment(R.id.fragment_container, accountSettingsFragment)
    }


}