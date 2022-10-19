package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WorkoutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_page)

        val maleButton = findViewById<Button>(R.id.btnMale)
        maleButton.setOnClickListener {
            val intent = Intent(this, WorkoutMale::class.java)
            startActivity(intent)
        }

        val femaleButton = findViewById<Button>(R.id.btnFemale)
        femaleButton.setOnClickListener {
            val intent = Intent(this, WorkoutFemale::class.java)
            startActivity(intent)
        }

        val nonBinaryButton = findViewById<Button>(R.id.btnNonBinary)
        nonBinaryButton.setOnClickListener {
            val intent = Intent(this, WorkoutNonBinary::class.java)
            startActivity(intent)
        }

    }

}
