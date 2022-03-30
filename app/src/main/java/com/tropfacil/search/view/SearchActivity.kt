package com.tropfacil.search.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.leust.data.Data
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.data.home_response
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.ActivitySearchBinding
import com.tropfacil.home.adapter.ViewPagerAdapterEvery
import com.tropfacil.home.view.HomeViewModel
import com.tropfacil.model.search.SearchInfo
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.search.SearchItemAdapter
import com.tropfacil.utils.ItemClickListener
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity() {

    lateinit var binding: ActivitySearchBinding
    lateinit var viewPagerSearch: ViewPagerAdapterEvery
    private val homeViewModel by inject<HomeViewModel>()
    private val preferenceProvider by inject<PreferenceProvider>()
    private var searchItemAdapter: SearchItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()
        initObserver()

    }

    private fun initObserver() {
        Data.token = preferenceProvider.getUserToken()
        if (Data.token != null)
            homeViewModel.HomeData(Data.token)

        lifecycleScope.launchWhenStarted {
            homeViewModel._syncItemsStateFlow.collect { homeresponse ->
                when (homeresponse) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(homeresponse.exception)
                    }
                    is SafeApiCall.Successhome -> {
                        binding.progressBar.isVisible = false
                        homeViewModel._syncItemsStateFlow.value
                        updateData(homeresponse.data)

                        //viewModel.syncGuestItems(getUUID())
                    }
                    else -> {
                        val s = ""
                    }
                }
            }
        }

    }

    private fun updateData(homeResponse: home_response) {
        val searchInfoList:ArrayList<SearchInfo> = ArrayList()
        for(currentData in homeResponse.themes){
            for(currentParcours in currentData.parcours){
                val currentParcourInfo= SearchInfo()
                currentParcourInfo.parentId = currentData.id
                currentParcourInfo.parentLibelle = currentData.libelle
                currentParcourInfo.childId = currentParcours.id
                currentParcourInfo.childLibelle = currentParcours.libelle
                currentParcourInfo.childDescription = currentParcours.description
                currentParcourInfo.image = currentParcours.image
                currentParcourInfo.type = currentParcours.type
                currentParcourInfo.dureeEstimee = currentParcours.duree_estimee
                currentParcourInfo.dureeSuivi = currentParcours.duree_suivi
                currentParcourInfo.verrouille = currentParcours.verrouille
                searchInfoList.add(currentParcourInfo)
                for(currentElement in currentParcours.elements){
                    val elementSearchInfo= SearchInfo()
                    elementSearchInfo.parentId = currentData.id
                    elementSearchInfo.parentLibelle = currentData.libelle
                    elementSearchInfo.childId = currentParcours.id
                    elementSearchInfo.childLibelle = currentParcours.libelle
                    elementSearchInfo.childDescription = currentParcours.description
                    elementSearchInfo.elementId = currentElement.id
                    elementSearchInfo.elementLibelle = currentElement.libelle
                    elementSearchInfo.elementDescription = currentElement.description
                    elementSearchInfo.image = currentElement.image
                    elementSearchInfo.type = currentElement.type
                    elementSearchInfo.dureeEstimee = currentElement.duree_estimee
                    elementSearchInfo.dureeSuivi = currentElement.duree_suivi.toString()
                    elementSearchInfo.verrouille = currentElement.verrouille
                    searchInfoList.add(elementSearchInfo)
                }
            }
        }
        searchItemAdapter?.updateList(searchInfoList)
    }

    fun setonClickListner() {
        binding.imgBack.setOnClickListener {
            finish()
        }

        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO ) {
                searchItem()
                true
            }
            false
        }

    }

    private fun searchItem() {
        var searchKeywords = edtSearch.text.toString().trim()

        if (searchKeywords == null)
            searchKeywords = ""

        searchItemAdapter?.filter?.filter(searchKeywords)
    }

    fun setData() {
        searchItemAdapter = SearchItemAdapter(this,object :ItemClickListener{
            override fun onItemClick(bundle: Bundle?) {
                // Write Code for On Item Click Here
            }
        })

        rv_search_items.layoutManager = LinearLayoutManager(this)
        rv_search_items.adapter = searchItemAdapter
    }


}