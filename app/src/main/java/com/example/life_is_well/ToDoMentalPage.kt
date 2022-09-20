package com.example.life_is_well


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.life_is_well.databinding.ActivityToDoMentalPageBinding


class ToDoMentalPage : AppCompatActivity() {
    private lateinit var binding: ActivityToDoMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToDoMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}