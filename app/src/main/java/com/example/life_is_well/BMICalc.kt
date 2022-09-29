package com.example.life_is_well

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat

class BMICalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicalc)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight, height)){
                // BMI equation for Standard Measurement System (Pounds & Inches)
                val bmi = (weight.toFloat()/((height.toFloat())*(height.toFloat()))) * 703
                // Setting the result to two decimal places, Stackoverflow came in clutch here
                val bmi2Decimals = String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Decimals)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "No Weight Entered.", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "No Height Entered.", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = getString(R.string.normal_range)

        var resultText = ""
        var color = 0
        // YouTube helped me with learning and apply these "when" statements compared to using if/else statements, this is much less coding.
        when{
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.colorUnderWeight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.colorNormal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.colorOverWeight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.colorObese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}