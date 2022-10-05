package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.life_is_well.databinding.ActivityMeditationMentalPageBinding


class MeditationMentalPage : AppCompatActivity() {
    private lateinit var binding: ActivityMeditationMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeditationMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    fun meditationBackBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
    }

    fun meditationHomeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }
}