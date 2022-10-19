package com.example.life_is_well.goalsPages

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.MainActivity
import com.example.life_is_well.ProfilePageMain
import com.example.life_is_well.R
import com.example.life_is_well.SettingsPageMain
import com.example.life_is_well.databinding.ActivityGoalsPageMainBinding
import com.example.life_is_well.databinding.GoalTaskListPageBinding
import com.example.life_is_well.goalsPages.goalsScreens.GoalTaskPageMain
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_goals_page_main.*

class GoalsPageMain : AppCompatActivity() {
    private lateinit var binding: ActivityGoalsPageMainBinding
    private lateinit var binding2: GoalTaskListPageBinding
    private lateinit var newGoalRecyclerView: RecyclerView
    private lateinit var newGoalButton: FloatingActionButton

    private lateinit var goalsList: ArrayList<GoalItem>

    private lateinit var goalItemIcons: Array<Int>
    private lateinit var goalItemTitles: Array<String>
    private lateinit var goalItemDescriptions: Array<String>

    private lateinit var tasksList: ArrayList<GoalItem>

    private lateinit var taskItemIcons: Array<Int>
    private lateinit var taskItemTitles: Array<String>
    private lateinit var taskItemDescriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalsPageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGoalRecyclerView = findViewById(R.id.goal_recycler_view)

        newGoalButton = findViewById(R.id.new_goal_button)
        goalItemIcons = arrayOf(R.drawable.finance, R.drawable.health, R.drawable.mental)
        goalItemTitles = arrayOf("Financial", "Physical Health", "Mental Health")
        goalItemDescriptions = arrayOf("Default Financial Goals", "Default Physical Health Goals", "Default Mental Health Goals")

        newGoalRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        newGoalRecyclerView.setHasFixedSize(true)
        newGoalButton.setOnClickListener { addGoalCardOnClick() }
        goals_bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileBottomNav -> {
                    val intent = Intent(this, ProfilePageMain::class.java)
                    startActivity(intent)
                }

                R.id.goalsBottomNav -> {
                    val intent = Intent(this, GoalsPageMain::class.java)
                    startActivity(intent)
                }


                R.id.homeBotNav -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.settingsBottomNav -> {
                    val intent = Intent(this, SettingsPageMain::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        goalsList = arrayListOf()
        getUserdata()
    }

    // TODO: Implement onClick logic. Needs to depend on something to distinguish between an always changing dynamic list of buttons and then set the listener. Issue: #52.
    private fun addGoalCardOnClick() : Unit {
        val newGoalItem = GoalItem("This is a new card", R.drawable.finance, "Newly Created item", goalsList.size)
        goalsList.add(newGoalItem)
        newGoalRecyclerView.adapter?.notifyDataSetChanged()
        Toast.makeText(this, "New Goal Created!", Toast.LENGTH_SHORT).show()
    }

    private fun goalCardOnClick(position: Int) : Unit {
        val intent = Intent(this, GoalTaskPageMain::class.java)
        startActivity(intent)
        Toast.makeText(this, goalsList[position].goalTitle, Toast.LENGTH_SHORT).show()
    }

    private fun getUserdata() {

        for (i in goalItemIcons.indices) {
            val goal = GoalItem(goalItemTitles[i], goalItemIcons[i], goalItemDescriptions[i], i)
            goalsList.add(goal)
        }

        newGoalRecyclerView.adapter = GoalAdapter(goalsList) { position: Int ->
            goalCardOnClick(
                position
            )
        }
    }
}
