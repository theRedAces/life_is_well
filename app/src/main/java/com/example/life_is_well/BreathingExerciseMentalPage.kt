package com.example.life_is_well

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.life_is_well.databinding.ActivityBreathingExerciseMentalPageBinding


class BreathingExerciseMentalPage : AppCompatActivity() {


    private lateinit var binding: ActivityBreathingExerciseMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreathingExerciseMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}