package com.example.life_is_well

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class PhysicalMacroTracker : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_macro_tracker)

        val btnDateClicker : Button = findViewById(R.id.btnDateClicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        btnDateClicker.setOnClickListener {

            clickDateClicker()
        }
    }

    private fun clickDateClicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this, "Year was $selectedYear, Month was ${selectedMonth + 1}, Day is $selectedDayOfMonth" , Toast.LENGTH_LONG).show()

            val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"

            tvSelectedDate?.setText(selectedDate)

            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

            val theDate =sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate.time / 60000

            val currentDate =sdf.parse(sdf.format(System.currentTimeMillis()))
        },
            year,
            month,
            day,
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}