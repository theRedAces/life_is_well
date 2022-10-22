package com.example.life_is_well.goalsPages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.google.android.material.imageview.ShapeableImageView

class GoalAdapter(private var goalsList: ArrayList<GoalItem>, private val goalCardOnClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
        return GoalViewHolder(view, goalCardOnClick)
    }
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bindValues(goalsList[position], holder)
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.goal_item
    }

    inner class GoalViewHolder(itemView: View, private val goalCardOnClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var goalIcon: ShapeableImageView? = null
        private var goalTitle: TextView? = null
        private var goalDescription: TextView? = null

        fun bindValues(currentGoal: GoalItem, holder: GoalViewHolder) {
            goalDescription = itemView.findViewById(R.id.goal_description)
            goalIcon = itemView.findViewById(R.id.goal_icon)
            goalTitle = itemView.findViewById(R.id.goal_title)

            holder.goalIcon?.setImageResource(currentGoal.goalIcon)
            holder.goalTitle?.text = currentGoal.goalTitle
            holder.goalDescription?.text = currentGoal.goalDescription
        }

        init {
            itemView.setOnClickListener((this))
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            goalCardOnClick(position)
        }
    }
}
