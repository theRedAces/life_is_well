package com.example.life_is_well.home

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.google.android.material.imageview.ShapeableImageView

class HomeButtonAdapter(private val c: Context, private var homeList: ArrayList<HomeUserData>, private val onHomeItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<HomeButtonAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_page_recyclable_button, parent, false)
        return HomeViewHolder(itemView, onHomeItemClicked)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentHomeBtn = homeList[position]
        holder.homeBtnIcon.setImageResource(currentHomeBtn.homeBtnIcon)
        holder.homeBtnName.text = currentHomeBtn.homeBtnTitle
        holder.homeBtnBackground.setImageResource(currentHomeBtn.homeBtnBackground)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    inner class HomeViewHolder(itemView: View, private val onHomeItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val homeBtnIcon: ShapeableImageView = itemView.findViewById(R.id.home_Btn_Icon)
        val homeBtnName: TextView = itemView.findViewById(R.id.home_Btn_Title)
        val homeBtnBackground: ImageView = itemView.findViewById(R.id.home_Btn_Background)

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            onHomeItemClicked(position)
        }

    }

}