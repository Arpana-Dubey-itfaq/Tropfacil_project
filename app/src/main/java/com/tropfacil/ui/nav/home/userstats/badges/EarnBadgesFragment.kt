package com.tropfacil.ui.nav.home.userstats.badges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tropfacil.R
import com.tropfacil.databinding.FragmentEarnBadgesBinding
import com.tropfacil.databinding.FragmentProfileBinding
import com.tropfacil.textCapSentences
import com.tropfacil.ui.nav.home.userstats.badges.adapter.EarnBadgeAdapter
import com.tropfacil.utils.ItemClickListener

class EarnBadgesFragment : Fragment() {
    lateinit var binding: FragmentEarnBadgesBinding
    private var earnBadgeAdapter:EarnBadgeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEarnBadgesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SetUI()
    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text =
            textCapSentences(getString(R.string.lbl_earn_badges))
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        earnBadgeAdapter = EarnBadgeAdapter(requireContext(),object :ItemClickListener{
            override fun onItemClick(bundle: Bundle?) {

            }
        })
        binding.recyclerView.adapter = earnBadgeAdapter
    }

}