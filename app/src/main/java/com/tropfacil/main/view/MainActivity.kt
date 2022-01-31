package com.tropfacil.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tropfacil.R
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.util.interfaces.HomeOptionsListener

class MainActivity : AppCompatActivity(), HomeOptionsListener {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToHomeScreen()

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


}