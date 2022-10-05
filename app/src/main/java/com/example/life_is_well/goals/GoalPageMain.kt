package com.example.life_is_well.goals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.databinding.ActivityGoalsPageMainBinding

class GoalsPageMain : AppCompatActivity() {
    private lateinit var binding: ActivityGoalsPageMainBinding

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var goalList: ArrayList<GoalItem>
    lateinit var imageId: Array<Int>
    lateinit var title: Array<String>
    lateinit var descriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalsPageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newRecyclerView = findViewById(R.id.recycler_view)
        imageId = arrayOf(R.drawable.finance, R.drawable.health, R.drawable.mental)
        title = arrayOf("Financial", "Physical Health", "Mental Health")
        descriptions = arrayOf("Default Financial Goals", "Default Physical Health Goals", "Default Mental Health Goals")

        newRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        newRecyclerView.setHasFixedSize(false)

        goalList = arrayListOf<GoalItem>()
        getUserdata()
    }


    private fun getUserdata() {

        for (i in imageId.indices) {
            val goal = GoalItem(title[i], imageId[i], descriptions[i])
            goalList.add(goal)
        }

        newRecyclerView.adapter = GoalAdapter(goalList)
    }
}
