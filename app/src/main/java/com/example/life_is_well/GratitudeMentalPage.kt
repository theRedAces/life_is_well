package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.life_is_well.databinding.ActivityGratitudeMentalPageBinding
import kotlinx.android.synthetic.main.activity_gratitude_mental_page.*

class GratitudeMentalPage : AppCompatActivity() {

    private lateinit var binding: ActivityGratitudeMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGratitudeMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    fun gratitudeHomeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }

    fun gratitudeBackBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
    }
}