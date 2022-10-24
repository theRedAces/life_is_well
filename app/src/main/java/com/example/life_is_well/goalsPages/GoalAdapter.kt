package com.example.life_is_well.goalsPages

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class GoalAdapter(private val context: Context, private var goalsList: ArrayList<GoalItem>, private val goalCardOnClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.goals_list_item, parent, false)
        return GoalViewHolder(view, goalCardOnClick)
    }
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bindValues(goalsList[position], holder)
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.goals_list_item
    }

    inner class GoalViewHolder(itemView: View, private val goalCardOnClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var goalIcon: ShapeableImageView? = null
        private var goalTitle: TextView? = null
        private var goalDescription: TextView? = null
        private var deleteGoalButton: Button? = null

        fun bindValues(currentGoal: GoalItem, holder: GoalViewHolder) {
            goalDescription = itemView.findViewById(R.id.goal_description)
            goalIcon = itemView.findViewById(R.id.goal_icon)
            goalTitle = itemView.findViewById(R.id.goal_title)
            deleteGoalButton = itemView.findViewById(R.id.delete_goal_button)

            holder.goalIcon?.setImageResource(currentGoal.goalIcon)
            holder.goalTitle?.text = currentGoal.goalTitle
            holder.goalDescription?.text = currentGoal.goalDescription
            holder.deleteGoalButton?.setOnClickListener{
                if (context is GoalsPageMain) {
                    context.deleteRecordAlertDialog(currentGoal)
                }
            }
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
