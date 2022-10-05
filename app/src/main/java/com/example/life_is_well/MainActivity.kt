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
import com.example.life_is_well.home.HomeUserData
import com.example.life_is_well.home.HomeButtonAdapter
import com.example.life_is_well.goals.GoalsPageMain
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var homeAddsBtn: FloatingActionButton
    private lateinit var homeRecv: RecyclerView
    private lateinit var homeUserList:ArrayList<HomeUserData>
    private lateinit var homeButtonAdapter: HomeButtonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeAddsBtn = findViewById(R.id.homeAddingBtn)
        homeRecv = findViewById(R.id.homeRecycler)
        homeUserList = ArrayList()
        homeButtonAdapter = HomeButtonAdapter(this, homeUserList)

        homeRecv.layoutManager = GridLayoutManager(this, 2)

        homeRecv.adapter = homeButtonAdapter

        homeAddsBtn.setOnClickListener { addInfo() }

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

    fun financeBtn(view: View) {
        val intent = Intent(this, FinancePageMain::class.java)
        startActivity(intent)
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.home_add_item_prompt, null)

        val userName = v.findViewById<EditText>(R.id.homeBtnName)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->

            val names = userName.text.toString()
            homeUserList.add(HomeUserData("$names"))
            homeButtonAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Account Creation Success", Toast.LENGTH_SHORT).show()

            dialog.dismiss()

        }
        addDialog.setNegativeButton("Cancel"){
                dialog, _->
            dialog.dismiss()
            Toast.makeText(this,"Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
    
    fun addBtn(view: View) {}

}
