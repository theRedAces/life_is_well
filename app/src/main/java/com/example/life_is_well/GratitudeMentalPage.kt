package com.example.life_is_well


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.life_is_well.databinding.ActivityGratitudeMentalPageBinding

class GratitudeMentalPage : AppCompatActivity() {

    private lateinit var binding: ActivityGratitudeMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGratitudeMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}