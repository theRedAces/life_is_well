package com.example.life_is_well.goals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.databinding.FragmentGoalsBinding

class GoalsPageMain : AppCompatActivity() {
    private lateinit var binding: FragmentGoalsBinding

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<GoalItem>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newRecyclerView = findViewById(R.id.recycler_view)
        imageId = arrayOf(R.drawable.finance, R.drawable.health, R.drawable.mental)
        heading = arrayOf("Financial Health", "Physical Health", "Mental Health")

        newRecyclerView.layoutManager = GridLayoutManager(this, 2)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<GoalItem>()
        getUserdata()
    }

    private fun getUserdata() {

        for (i in imageId.indices) {
            val goal = GoalItem(heading[i], imageId[i])
            newArrayList.add(goal)
        }

        newRecyclerView.adapter = GoalAdapter(newArrayList)
    }
}
