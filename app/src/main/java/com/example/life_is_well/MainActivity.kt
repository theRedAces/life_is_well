package com.example.life_is_well

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.databinding.ActivityMainBinding
import com.example.life_is_well.goals.GoalsPageMain
import com.example.life_is_well.home.HomeButtonAdapter
import com.example.life_is_well.home.HomeUserData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_add_item_prompt.*

class MainActivity : AppCompatActivity() {
    private lateinit var financeBtn: FloatingActionButton
    private lateinit var healthBtn: FloatingActionButton
    private lateinit var mentalBtn: FloatingActionButton
    private lateinit var addsBtn: FloatingActionButton


    private lateinit var binding: ActivityMainBinding
    private lateinit var homeRecyclerView: RecyclerView

    private lateinit var homeList: ArrayList<HomeUserData>
    private lateinit var imageId: Array<Int>
    private lateinit var title: Array<String>
    private lateinit var backgroundColor: Array<Int>

    private lateinit var homeButtonAdapter: HomeButtonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addsBtn = findViewById(R.id.homeAddingBtn)
        financeBtn = findViewById(R.id.financeBtn)
        healthBtn = findViewById(R.id.healthBtn)
        mentalBtn = findViewById(R.id.mentalBtn)


        homeRecyclerView = findViewById(R.id.homeRecycler)
        imageId = arrayOf(R.drawable.finance, R.drawable.health, R.drawable.mental)
        title = arrayOf("Finance", "Health", "Mental")
        backgroundColor = arrayOf(R.drawable.home_page_finance_btn_background,R.drawable.home_page_health_btn_background,R.drawable.home_page_mental_btn_background)

        homeRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        homeRecyclerView.setHasFixedSize(false)

        homeList = arrayListOf()
        addsBtn.setOnClickListener { addInfo() }
        financeBtn.setOnClickListener { financeBtn() }
        healthBtn.setOnClickListener { healthBtn() }
        mentalBtn.setOnClickListener { mentalBtn() }
        homeButtonAdapter = HomeButtonAdapter(this, homeList){position: Int -> onHomeListItemClick(position)}
        getUserdata()


        home_bottom_nav.setOnItemSelectedListener {
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

    private fun onHomeListItemClick(position: Int) : Unit {
        Toast.makeText(this, homeList[position].homeBtnTitle, Toast.LENGTH_SHORT).show()
    }


    private fun getUserdata() {

        for (i in imageId.indices) {
            val homeBtn = HomeUserData(title[i], imageId[i], backgroundColor[i])
            homeList.add(homeBtn)
        }

        homeRecyclerView.adapter = HomeButtonAdapter(this, homeList){position: Int ->
            onHomeListItemClick(position)
        }
    }


    private fun financeBtn(){
        val intent = Intent(this, FinancePageMain::class.java)
        startActivity(intent)
    }
    private fun healthBtn(){
        val intent = Intent(this, HealthPageMain::class.java)
        startActivity(intent)
    }
    private fun mentalBtn(){
        val intent = Intent(this, MentalPageMain::class.java)
        startActivity(intent)
    }


    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.home_add_item_prompt, null)

        val userName = v.findViewById<EditText>(R.id.homeBtnNamePrompt)
        val userIcon = v.findViewById<EditText>(R.id.homeBtnIconPrompt)
        val userBack = v.findViewById<EditText>(R.id.homeBtnBackgroundPrompt)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Accept"){
                dialog,_->


            val names = userName.text.toString()
            val icon = userIcon.text.toString().toInt()
            val bckgrnd = userBack.text.toString().toInt()


            homeList.add(HomeUserData(names,imageId[icon-1],backgroundColor[bckgrnd-1]))
            homeButtonAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }
        addDialog.setNegativeButton("Cancel"){
                dialog, _->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

}
