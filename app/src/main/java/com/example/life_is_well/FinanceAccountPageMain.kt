package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FinanceAccountPageMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_account_page_main)
    }

    fun accountBackBtn(view: View) {
        val intent = Intent(this,FinancePageMain::class.java )
        startActivity(intent)
    }
}