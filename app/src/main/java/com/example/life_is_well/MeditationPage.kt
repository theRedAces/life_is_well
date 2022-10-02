package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.life_is_well.databinding.ActivityMeditationMentalPageBinding


class MeditationPage : AppCompatActivity() {
    private lateinit var binding: ActivityMeditationMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeditationMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DailyMeditation Button
        val DailyMeditationButton = findViewById<Button>(R.id.link_dailyMed)
        DailyMeditationButton.setOnClickListener {
            val intent = Intent(this, DailyMeditationPage::class.java)
            startActivity(intent)
        }

        val OpenHeartButton = findViewById<Button>(R.id.openHeart_view)
        OpenHeartButton.setOnClickListener {
            val intent = Intent(this, OpenHeartMeditationPage::class.java)
            startActivity(intent)
        }

        val LetGoButton = findViewById<Button>(R.id.link_letGo)
        LetGoButton.setOnClickListener {
            val intent = Intent(this, LettingGoMeditationPage::class.java)
            startActivity(intent)
        }

        val SuperPerformanceButton = findViewById<Button>(R.id.link_super_performance)
        SuperPerformanceButton.setOnClickListener {
            val intent = Intent(this, SuperPerformanceMeditationPage::class.java)
            startActivity(intent)
        }

    }

    fun backBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
        }

    fun homeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }
}