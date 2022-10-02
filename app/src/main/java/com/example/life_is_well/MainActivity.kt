package com.example.life_is_well


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.goals.GoalsPageMain

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




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

    fun healthBtn(view: View) {
        val intent = Intent(this, HealthPageMain::class.java)
        startActivity(intent)
    }

    fun mentalBtn(view: View) {
        val intent = Intent(this, MentalPageMain::class.java)
        startActivity(intent)
    }


    fun addBtn(view: View) {}


}