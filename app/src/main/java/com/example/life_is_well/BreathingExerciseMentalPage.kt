package com.example.life_is_well

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.databinding.ActivityBreathingExerciseMentalPageBinding
import com.example.life_is_well.goalsPages.GoalsPageMain
import kotlinx.android.synthetic.main.content_breathing_exercise_mental_page.*


class BreathingExerciseMentalPage : AppCompatActivity() {

    private lateinit var binding: ActivityBreathingExerciseMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreathingExerciseMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun breathingBackBtn(view: View) {
        val intent = Intent(this, MentalPageMain::class.java)
        startActivity(intent)
    }


    fun homeBtn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}