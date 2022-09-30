package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.life_is_well.databinding.ActivityDailyMeditationPageBinding

class DailyMeditationPage : AppCompatActivity() {
    private lateinit var binding: ActivityDailyMeditationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDailyMeditationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun backBtn(view: View) {
        val intent = Intent(this,MeditationPage::class.java )
        startActivity(intent)
    }

    fun homeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }
}