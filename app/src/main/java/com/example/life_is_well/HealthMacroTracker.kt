package com.example.life_is_well

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.life_is_well.goalsPages.GoalsPageMain
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import macros.MealAdapter
import macros.MealData
import kotlinx.android.synthetic.main.activity_health_macro_tracker.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HealthMacroTracker : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var mealList:ArrayList<MealData>
    private lateinit var mealAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_macro_tracker)
        /** RecyclerView Code*/
        /** set List*/
        mealList = ArrayList()
        /** set find ID*/
        addsBtn = findViewById(R.id.healthAddBtn)
        recv = findViewById(R.id.macroRecycler)
        /** set Adapter*/
        mealAdapter = MealAdapter(this, mealList)
        /** set RecyclerView Adapter*/
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = mealAdapter
        /** set Dialog*/
        addsBtn.setOnClickListener{ addInfo()}

        /** Calendar Date Picker Code */
        val btnDateClicker : Button = findViewById(R.id.btnDateClicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        btnDateClicker.setOnClickListener {

            clickDateClicker()
        }

        health_macro_bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileBottomNav -> {
                    val intent = Intent(this, ProfilePageMain::class.java)
                    startActivity(intent)
                }

                R.id.goalsBottomNav -> {
                    val intent = Intent(this, GoalsPageMain::class.java)
                    startActivity(intent)
                }


                R.id.homeBotNav -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.settingsBottomNav -> {
                    val intent = Intent(this, SettingsPageMain::class.java)
                    startActivity(intent)
                }
            }
            true
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



    fun macroBackBtn(view: View) {
        val intent = Intent(this,HealthPageMain::class.java )
        startActivity(intent)
    }

    fun macroHomeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_meal,null)
        /** set View */
        val userMeal = v.findViewById<EditText>(R.id.etMeal)
        val userFood = v.findViewById<EditText>(R.id.etFoods)
        val userCalories = v.findViewById<EditText>(R.id.etCalories)
        val userOunces = v.findViewById<EditText>(R.id.etOunces)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") {
            dialog, _->
            val meals = userMeal.text.toString()
            val foods = userFood.text.toString()
            val calories = userCalories.text.toString()
            val ounces = userOunces.text.toString()
            mealList.add(MealData("Meal: $meals", "Foods and Beverages: $foods", "Calories: $calories", "Ounces: $ounces" ))
            mealAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Added Meal Information Succeeded", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") {
            dialog, _->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }



}