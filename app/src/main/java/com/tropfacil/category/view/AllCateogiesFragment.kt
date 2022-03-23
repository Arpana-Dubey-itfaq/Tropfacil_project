package com.tropfacil.category.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import androidx.core.widget.NestedScrollView
import android.graphics.PorterDuff


import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.tropfacil.base.BaseActivity
import com.tropfacil.category.adapter.CategoriesListAdapter
import com.tropfacil.category.model.CategoryList
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.databinding.FragmentCatgoryBinding
import com.tropfacil.message.view.MessageActivity
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.util.Constants


class AllCateogiesFragment : BaseFragment(), ResumeFragmentListener {
    lateinit var binding: FragmentCatgoryBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var categoriesListAdapter: CategoriesListAdapter


    companion object {
        const val TAG = "AllCateogiesFragment"

        @JvmStatic
        fun newInstance() = AllCateogiesFragment().apply {

        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }

    fun createdummyData(): MutableList<CategoryList> {
        var arrayList: MutableList<CategoryList> = mutableListOf()
        arrayList.add(CategoryList(1, "Maths"))
        arrayList.add(CategoryList(2, "Science"))
        arrayList.add(
            CategoryList(
                3,
                "Maths"
            )
        )
        arrayList.add(CategoryList(4, "Economics"))
        arrayList.add(CategoryList(5, "English"))
        return arrayList
    }

    fun setData() {
        categoriesListAdapter = CategoriesListAdapter(createdummyData())
        binding.relcategory.adapter = categoriesListAdapter

        binding.topbar.cardChatcount.visibility = View.GONE
    }

    fun setObservers() {
        categoriesListAdapter.selectedItems.observe(viewLifecycleOwner, {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCatgoryBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
        setObservers()
    }

    fun setListner() {
        binding.topbar.imgUser.setOnClickListener {
            homeOptionsListener.onClickMenu()
        }
        binding.topbar.imgsearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.topbar.imgNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))

        }
        binding.topbar.imgmessage.setOnClickListener {
            (requireActivity() as BaseActivity).updateResumeFragment(this)
            val writeAMessageFragment = WriteAMessageFragment()
            (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, writeAMessageFragment)

//            startActivity(Intent(requireContext(), MessageActivity::class.java))

        }
/*
        binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.view1.visibility = View.GONE
                binding.lblRecommendExcrcise.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                Log.i(TAG, "Scroll DOWN")
            }
            if (scrollY < oldScrollY) {

                Log.i(TAG, "Scroll UP")
            }
            if (scrollY == 0) {
                binding.view1.visibility = View.VISIBLE
                binding.lblRecommendExcrcise.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                Log.i(TAG, "TOP SCROLL")
            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                Log.i(TAG, "BOTTOM SCROLL")
            }
        })
*/
    }

    override fun onFragmentResume(bundle: Bundle?) {

    }
}