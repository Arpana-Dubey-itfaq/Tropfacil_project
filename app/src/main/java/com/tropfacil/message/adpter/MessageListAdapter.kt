package com.tropfacil.message.adpter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.databinding.*
import com.tropfacil.message.model.User
import com.tropfacil.message.view.StickyHeaderItemDecoration

const val TYPE_HEADER = 0
const val TYPE_SENDERITEM = 1
const val TYPE_RECEIVERITEM = 2


class MessageListAdapter(
    val users: List<User>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickyHeaderItemDecoration.StickyHeaderInterface {


    inner class ViewSenderHolder(val bind: ItemChatSenderBinding) :
        RecyclerView.ViewHolder(bind.root)

    inner class ViewReceiverHolder(val bind: ItemChatReceiverBinding) :
        RecyclerView.ViewHolder(bind.root)

    inner class ViewHeaderHolder(val bind: ItemChatDateTimeBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        /*  val binding: ItemNotificationBinding =
              ItemNotificationBinding.inflate(
                  LayoutInflater.from(parent.context),
                  parent,
                  false
              )*/

        return if (viewType == TYPE_HEADER) {
            ViewHeaderHolder(
                ItemChatDateTimeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == TYPE_SENDERITEM) {
            ViewSenderHolder(
                ItemChatSenderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        } else {
            ViewReceiverHolder(
                ItemChatReceiverBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }


    override fun getItemCount(): Int {
        return users.size
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        var position = itemPosition
        do {
            if (this.isHeader(position)) {
                headerPosition = position
                break
            }
            position -= 1
        } while (position >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.item_chat_date_time
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        ((header as ConstraintLayout).getChildAt(0) as TextView).text =
            users[headerPosition].name
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return users[itemPosition].header
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewSenderHolder) {
            holder.bind.tvMessage.text = users[position].name
        } else if (holder is ViewReceiverHolder) {
            holder.bind.tvMessage.text = users[position].name
        } else if (holder is ViewHeaderHolder) {
            holder.bind.tvDate.text = users[position].name
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (users[position].header) {
            TYPE_HEADER
        } else if (users[position].isSender) {
            TYPE_SENDERITEM
        } else {
            TYPE_RECEIVERITEM
        }
    }
    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}