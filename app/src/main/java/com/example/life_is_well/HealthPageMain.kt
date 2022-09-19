package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class HealthPageMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val macrosButton = findViewById<Button>(R.id.macroTrackerButton)
        macrosButton.setOnClickListener {
            val Intent = Intent(this, PhysicalMacroTracker::class.java)
            startActivity(Intent)

        }
    }
}
    /*fun healthBackBtn(view: View) {

        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
        }
    */

