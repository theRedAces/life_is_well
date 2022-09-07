package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun financeBtn(view: View) {
        val intent = Intent(this,FinancePageMain::class.java )
        startActivity(intent)

    }

    fun healthBtn(view: View) {
        val intent = Intent(this,HealthPageMain::class.java )
        startActivity(intent)

    }

    fun mentalBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)

    }

    fun addBtn(view: View) {}
}