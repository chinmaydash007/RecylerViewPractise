package com.example.demowithrecylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ChatsAdapter(var chatList: List<Chat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var view: View
        if (viewType == 0) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rigth_chat_layout, parent, false)
            return SenderViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.left_chat_layout, parent, false)
            return ReceiverViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            var senderViewHolder = holder as SenderViewHolder
            senderViewHolder.senderTextView.text = chatList.get(position).msg
        } else {
            var receiverViewHolder = holder as ReceiverViewHolder
            receiverViewHolder.receiverTextView.text = chatList.get(position).msg
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (chatList.get(position).isSender) {
            return 0;
        } else {
            return 1;
        }
    }

    inner class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var senderTextView: TextView = itemView.findViewById(R.id.textView)
    }

    inner class ReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var receiverTextView: TextView = itemView.findViewById(R.id.textView)

    }
}