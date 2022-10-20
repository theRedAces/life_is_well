package com.example.life_is_well.goalsPages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.goalsPages.goalsScreens.GoalTaskItem
import com.google.android.material.imageview.ShapeableImageView

class GoalTaskAdapter(private var tasksList: ArrayList<GoalTaskItem>) :
    RecyclerView.Adapter<GoalTaskAdapter.GoalTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_task_list_item, parent, false)
        return GoalTaskViewHolder(view)
    }
    override fun onBindViewHolder(holder: GoalTaskViewHolder, position: Int) {
        holder.bindValues(tasksList[position], holder)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.goal_task_list_item
    }

    inner class GoalTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var taskIcon: ShapeableImageView? = null
        private var taskTitle: TextView? = null
        private var taskDescription: TextView? = null

        fun bindValues(currentGoal: GoalTaskItem, holder: GoalTaskViewHolder) {
            taskDescription = itemView.findViewById(R.id.taskDescription)
            taskIcon = itemView.findViewById(R.id.taskIcon)
            taskTitle = itemView.findViewById(R.id.taskName)

            holder.taskIcon?.setImageResource(currentGoal.taskIcon)
            holder.taskTitle?.text = currentGoal.taskTitle
            holder.taskDescription?.text = currentGoal.taskDescription
        }
    }
}
