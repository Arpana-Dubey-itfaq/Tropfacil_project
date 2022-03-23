package com.tropfacil.message.view

import android.os.Bundle
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.*
import com.tropfacil.message.adpter.MessageListAdapter
import com.tropfacil.message.model.User

class MessageActivity : BaseActivity() {

    lateinit var binding: ActivityMessageBinding
    lateinit var adapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setonClickListner()
        setData()

    }

    fun setonClickListner() {
        binding.incTopbar.imgBack.setOnClickListener {
            finish()
        }


    }

    fun setData() {
        binding.incTopbar.itemHeader.text = getString(R.string.message)
        adapter = MessageListAdapter(createDummyObjects())
        binding.relMessage.adapter = adapter
        binding.relMessage.addItemDecoration(
            StickyHeaderItemDecoration(
                binding.relMessage,
                adapter as MessageListAdapter
            )
        )


    }

    fun createDummyObjects(): List<User> {
        val dummyObjects = ArrayList<User>()
        for (i in 1..5) {  //Repeating to just create more objects to show the scroll.
            dummyObjects += User("Year 1987", -1, true,false)
            dummyObjects += User("kevin", 542366390, false,true)
            dummyObjects += User("Christiano", 552129590, false,false)
            dummyObjects += User("Year 1990", -1, true,false)
            dummyObjects += User("Jake", 631531190, false,true)
            dummyObjects += User("Claire", 644231990, false,false)
            dummyObjects += User("Year 1992", -1, true,true)
            dummyObjects += User("Kumar", 697108790, false,false)
            dummyObjects += User("Harold", 715339190, false,true)
        }
        return dummyObjects
    }

}