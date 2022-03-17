package com.tropfacil

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.main.view.MainActivity
import com.tropfacil.util.interfaces.HomeOptionsListener

class Dashboard : BaseActivity(), HomeOptionsListener {
    internal lateinit var drawerToggle: ActionBarDrawerToggle
    internal lateinit var drawerLayout: DrawerLayout
    internal lateinit var toolbar: Toolbar
lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var binding: ActivityHomeBinding
    internal lateinit var fragmentManager: FragmentManager
    internal lateinit var navigationView: NavigationView
    internal lateinit var frameLayout: FrameLayout


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        fragmentManager = supportFragmentManager
        homeOptionsListener=this
        setupView()
        if (savedInstanceState == null) showHome()
    }

    private fun setupView() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        frameLayout = findViewById(R.id.fragment_container) as FrameLayout

        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)

        navigationView = findViewById(R.id.navigation_view) as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun showHome() {
        selectDrawerItem(navigationView.menu.getItem(0))
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        var specialToolbarBehaviour = false
        val fragmentClass: Class<*>

        when (menuItem.itemId) {
            R.id.navHome -> fragmentClass = HomeFragment::class.java

            else -> fragmentClass = HomeFragment::class.java
        }

        try {
            val fragment = fragmentClass.newInstance() as Fragment
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //setToolbarElevation(specialToolbarBehaviour)
        menuItem.isChecked = true
        title = menuItem.title
        drawerLayout.closeDrawers()
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        drawerToggle.syncState()
        super.onPostCreate(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onClickMenu() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun lockDrawer(lock: Boolean) {
        if (lock) {
         drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
           drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }



    override fun navigateToHomeScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment.newInstance(), HomeFragment.TAG).commit()

    }

    override fun navigateToMyCoursesScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToMyExerciseScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToCategoryScreen() {
        TODO("Not yet implemented")
    }
}