package com.example.life_is_well.goals

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.google.android.material.imageview.ShapeableImageView

class GoalAdapter(private val mContext: Context, private var goalsList: ArrayList<GoalItem>, private val onGoalItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        if (viewType == R.layout.goal_item) {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
                return GoalViewHolder(view, onGoalItemClicked)
            } else {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.add_new_button, parent, false)
                return GoalViewHolder(view, onGoalItemClicked)
            }
    }
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.addGoalButton?.visibility = View.GONE
        if (position == goalsList.size - 1) {
            holder.addGoalButton?.visibility = View.VISIBLE
        }
        if (getItemViewType(position) == R.layout.add_new_button) {
            holder.bindButton(holder)
        } else {
            val currentGoal = goalsList[position]
            holder.bindValues(currentGoal, holder)
        }
    }

    override fun getItemCount(): Int {
        return goalsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == goalsList.size) {
            return R.layout.add_new_button
        } else {
            return R.layout.goal_item
        }
    }

    inner class GoalViewHolder(itemView: View, private val onGoalItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var goalIcon: ShapeableImageView? = null
        private var goalTitle: TextView? = null
        private var goalDescription: TextView? = null
        var addGoalButton: Button? = null

        fun bindValues(currentGoal: GoalItem, holder: GoalViewHolder) {
            goalDescription = itemView.findViewById(R.id.goal_description)
            goalIcon = itemView.findViewById(R.id.goal_icon)
            goalTitle = itemView.findViewById(R.id.goal_title)

            holder.goalIcon?.setImageResource(currentGoal.goalIcon)
            holder.goalTitle?.text = currentGoal.goalTitle
            holder.goalDescription?.text = currentGoal.goalDescription
        }

        fun bindButton(holder: GoalViewHolder) {
            addGoalButton = itemView.findViewById(R.id.add_goal_button)
            holder.addGoalButton?.text = mContext.getString(R.string.addNewGoalButtonText)
            addGoalButton?.setOnClickListener {
                val newGoalItem = GoalItem("New Financial Goal", R.drawable.finance, "This is a goal card added by button click.")
                goalsList = goalsList.plus(newGoalItem) as ArrayList<GoalItem>
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            onGoalItemClicked(position)
        }
    }
}
