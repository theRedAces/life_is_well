package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.life_is_well.databinding.ActivityMeditationPageBinding


class MeditationPage : AppCompatActivity() {
    private lateinit var binding: ActivityMeditationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeditationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breathingExButton = findViewById<Button>(R.id.link_breathingExercise)
        breathingExButton.setOnClickListener {
            val intent = Intent(this, BreathingExerciseMentalPage::class.java)
            startActivity(intent)
        }

        // DailyMeditation Button
        val DailyMeditationBtn = findViewById<Button>(R.id.link_dailyMed)
        DailyMeditationBtn.setOnClickListener {
            val intent = Intent(this, DailyMeditationPage::class.java)
            startActivity(intent)
        }

        val OpenHeartButton = findViewById<Button>(R.id.openHeartView)
        OpenHeartButton.setOnClickListener {
            val intent = Intent(this, OpenHeartMeditationPage::class.java)
            startActivity(intent)
        }

        val LetGoButton = findViewById<Button>(R.id.wishlist_button)
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