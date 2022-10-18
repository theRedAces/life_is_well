package com.example.life_is_well.goals

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
import kotlinx.android.synthetic.main.activity_goals_page_main.*
import kotlinx.android.synthetic.main.activity_main.*

class GoalsPageMain : AppCompatActivity() {
    private lateinit var binding: ActivityGoalsPageMainBinding
    private lateinit var newRecyclerView: RecyclerView

    private lateinit var goalList: ArrayList<GoalItem>

    private lateinit var imageIds: Array<Int>
    private lateinit var titles: Array<String>
    private lateinit var descriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalsPageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newRecyclerView = findViewById(R.id.recycler_view)
        imageIds = arrayOf(R.drawable.finance, R.drawable.health, R.drawable.mental)
        titles = arrayOf("Financial", "Physical Health", "Mental Health")
        descriptions = arrayOf("Default Financial Goals", "Default Physical Health Goals", "Default Mental Health Goals")

        newRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        newRecyclerView.setHasFixedSize(true)

        goalList = arrayListOf()
        getUserdata()



        goals_bot_nav.setOnItemSelectedListener {
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


    }

    // TODO: Implement onClick logic. Needs to depend on something to distinguish between an always changing dynamic list of buttons and then set the listener. Issue: #52.
    private fun onGoalListItemClick(position: Int) : Unit {
        Toast.makeText(this, goalList[position].goalTitle, Toast.LENGTH_SHORT).show()
    }

    private fun getUserdata() {

        for (i in imageIds.indices) {
            val goal = GoalItem(titles[i], imageIds[i], descriptions[i])
            goalList.add(goal)
        }

        newRecyclerView.adapter = GoalAdapter(this, goalList) { position: Int ->
            onGoalListItemClick(
                position
            )
        }
    }
}
