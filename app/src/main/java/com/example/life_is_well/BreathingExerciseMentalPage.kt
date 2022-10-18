package com.example.life_is_well

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.databinding.ActivityBreathingExerciseMentalPageBinding
import com.example.life_is_well.goals.GoalsPageMain
import kotlinx.android.synthetic.main.activity_mental_page_main.*
import kotlinx.android.synthetic.main.content_breathing_exercise_mental_page.*


class BreathingExerciseMentalPage : AppCompatActivity() {


    private lateinit var binding: ActivityBreathingExerciseMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreathingExerciseMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mental_breath_bottom_nav.setOnItemSelectedListener {
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
    fun breathingBackBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
    }


    fun breathingHomeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }
}