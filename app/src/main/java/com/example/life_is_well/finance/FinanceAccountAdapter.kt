package com.example.life_is_well.finance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.finance.FinanceUserData

class UserAdapter (private val c:Context,private val userList:ArrayList<FinanceUserData>, private val onFinanceItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.finance_page_recyclable_button,parent,false)
        return UserViewHolder(itemView, onFinanceItemClicked)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.accountName

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View, private val onFinanceItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val name: TextView = itemView.findViewById<TextView>(R.id.mTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            onFinanceItemClicked(position)
        }

    }

}