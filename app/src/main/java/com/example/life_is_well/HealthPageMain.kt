package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.life_is_well.goals.GoalsPageMain
import kotlinx.android.synthetic.main.activity_health_macro_tracker.*
import kotlinx.android.synthetic.main.activity_health_page_main.*

class HealthPageMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_page_main)

        val macrosButton = findViewById<Button>(R.id.macroTrackerButton)
        macrosButton.setOnClickListener {
            val intent = Intent(this, HealthMacroTracker::class.java)
            startActivity(intent)

        }

        val bmiButton = findViewById<Button>(R.id.bmiCalc)
        bmiButton.setOnClickListener {
            val intent = Intent(this,BMICalc::class.java)
            startActivity(intent)
        }

        health_main_bottom_nav.setOnItemSelectedListener {
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

    fun healthBackBtn(view: View) {

        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)


    }
}



