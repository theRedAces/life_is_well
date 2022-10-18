package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.life_is_well.databinding.ActivityMentalPageMainBinding
import com.example.life_is_well.goalsPages.GoalsPageMain
import kotlinx.android.synthetic.main.activity_health_macro_tracker.*
import kotlinx.android.synthetic.main.activity_mental_page_main.*

class MentalPageMain : AppCompatActivity() {
    private lateinit var binding: ActivityMentalPageMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMentalPageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_mental_page_main)

       val meditateButton = findViewById<ImageButton>(R.id.imageButton6)
        meditateButton.setOnClickListener {
            val intent = Intent(this, MeditationPage::class.java)
            startActivity(intent)
        }

        val gratitudeJournalButton = findViewById<ImageButton>(R.id.imageButton7)
        gratitudeJournalButton.setOnClickListener {
            val intent = Intent(this, GratitudeMentalPage::class.java)
            startActivity(intent)
        }

        val breathingExButton = findViewById<ImageButton>(R.id.imageButton8)
        breathingExButton.setOnClickListener {
            val intent = Intent(this, BreathingExerciseMentalPage::class.java)
            startActivity(intent)
        }

        val toDoButton = findViewById<ImageButton>(R.id.imageButton9)
       toDoButton.setOnClickListener {
            val intent = Intent(this, ToDoMentalPage::class.java)
            startActivity(intent)
        }

        mental_main_bottom_nav.setOnItemSelectedListener {
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


    fun mentalBackBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }

    fun homeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }




}