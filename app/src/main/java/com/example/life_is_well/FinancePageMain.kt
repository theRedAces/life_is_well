package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FinancePageMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_page_main)
    }

    fun financeBackBtn(view: View) {

        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)

    }


    fun accountBtnMain(view: View) {}
    fun financeAddBtn(view: View) {}

}