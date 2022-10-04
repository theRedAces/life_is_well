package com.example.life_is_well.goals

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.google.android.material.imageview.ShapeableImageView

class GoalAdapter(private val goalsList: ArrayList<GoalItem>) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)

        return GoalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val currentGoal = goalsList[position]
        holder.goalIcon.setImageResource(currentGoal.goalIcon)
        holder.goalTitle.text = currentGoal.goalTitle
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

    class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val goalIcon : ShapeableImageView = itemView.findViewById(R.id.goal_icon)
        val goalTitle : TextView = itemView.findViewById(R.id.goal_title)

    }
}
