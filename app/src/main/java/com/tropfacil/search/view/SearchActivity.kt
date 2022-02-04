package com.tropfacil.search.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.ActivityHomeBinding
import com.tropfacil.databinding.ActivitySearchBinding
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import com.tropfacil.home.adapter.ViewPagerAdapter

class SearchActivity : BaseActivity() {

    lateinit var binding: ActivitySearchBinding
    lateinit var viewPagerSearch: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()
        setTabLayout()

    }

    fun setonClickListner() {
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun setData() {
        viewPagerSearch = ViewPagerAdapter(this, 2)
        binding.viewPagersearch.adapter = viewPagerSearch

    }

    fun setTabLayout() {


        TabLayoutMediator(binding.tabsearch, binding.viewPagersearch) { tab, position ->
            /* val tabView = LayoutInflater.from(this.context)
                 .inflate(R.layout.custom_tab_recommeded_exercise, binding.tabLayoutExercise, false)
*/
            // tab.setCustomView(R.layout.custom_tab_recommeded_exercise);


            when (position) {
                0 -> {
                    tab.text = getString(R.string.course)
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    tab.text = getString(R.string.exercise)
                    //   tabview.tvExerciseName.text = "hfgdsghf"

                    // you can set your tab text and color here for tab2
                }

            }
        }.attach()


    }
}