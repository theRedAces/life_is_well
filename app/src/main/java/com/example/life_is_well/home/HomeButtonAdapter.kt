package com.example.life_is_well.home

import android.content.Context
import android.net.wifi.hotspot2.pps.HomeSp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.home.HomeUserData

class HomeButtonAdapter (val c:Context,val homeUserList:ArrayList<HomeUserData>):RecyclerView.Adapter<HomeButtonAdapter.UserViewHolder>()
{
    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v){

        val name = v.findViewById<TextView>(R.id.mTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.home_page_recyclable_button,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = homeUserList[position]
        holder.name.text = newList.buttonName

    }

    override fun getItemCount(): Int {
        return homeUserList.size
    }
}