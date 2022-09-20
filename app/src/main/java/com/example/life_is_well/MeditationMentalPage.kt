package com.example.life_is_well


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.life_is_well.databinding.ActivityMeditationMentalPageBinding


class MeditationMentalPage : AppCompatActivity() {
    private lateinit var binding: ActivityMeditationMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeditationMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}