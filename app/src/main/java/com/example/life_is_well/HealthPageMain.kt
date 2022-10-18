package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
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

        val workoutButton = findViewById<Button>(R.id.btnWorkout)
        workoutButton.setOnClickListener {
            val intent = Intent(this,WorkoutPage::class.java)
            startActivity(intent)
        }
    }

    fun healthBackBtn(view: View) {

        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)


    }
}



