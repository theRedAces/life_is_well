package com.example.life_is_well.goalsPages.goalsScreens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.databinding.GoalTaskListPageBinding
import com.example.life_is_well.goalsPages.GoalTaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalTaskPageMain : AppCompatActivity() {
    private lateinit var binding: GoalTaskListPageBinding
    private lateinit var newTaskRecyclerView: RecyclerView
    private lateinit var newTaskButton: FloatingActionButton

    private lateinit var tasksList: ArrayList<GoalTaskItem>

    private lateinit var taskItemIcons: Array<Int>
    private lateinit var taskItemTitles: Array<String>
    private lateinit var taskItemDescriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GoalTaskListPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newTaskRecyclerView = findViewById(R.id.task_recycler_view)
        newTaskButton = findViewById(R.id.new_task_button)
        taskItemIcons = arrayOf(R.drawable.finance, R.drawable.mental, R.drawable.health)
        taskItemTitles = arrayOf("Task for Finances", "Task for Mentals", "Task for Physicals")
        taskItemDescriptions = arrayOf("This is where the description of the task goes", "Same here...", "...And here")

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        newTaskRecyclerView.layoutManager = layoutManager
        newTaskRecyclerView.layoutManager
        newTaskRecyclerView.setHasFixedSize(true)
        newTaskButton.setOnClickListener { addTaskItemOnClick() }

        tasksList = arrayListOf()
        getUserdata()
    }

    // TODO: Implement onClick logic. Needs to depend on something to distinguish between an always changing dynamic list of buttons and then set the listener. Issue: #52.
    private fun addTaskItemOnClick() : Unit {
        val newGoalItem = GoalTaskItem("This is a new task", R.drawable.finance, "Newly Created task", tasksList.size)
        tasksList.add(newGoalItem)
        Toast.makeText(this, "New Goal Created!", Toast.LENGTH_SHORT).show()
    }

    private fun getUserdata() {
        for (i in taskItemIcons.indices) {
            val goal = GoalTaskItem(taskItemTitles[i], taskItemIcons[i], taskItemDescriptions[i], i)
            tasksList.add(goal)
        }

        newTaskRecyclerView.adapter = GoalTaskAdapter(tasksList)
    }
}