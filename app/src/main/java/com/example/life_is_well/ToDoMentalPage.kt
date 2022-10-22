package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.life_is_well.databinding.ActivityToDoMentalPageBinding


class ToDoMentalPage : AppCompatActivity() {
    private lateinit var binding: ActivityToDoMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToDoMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mental_toDo_bottom_nav.setOnItemSelectedListener {
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

        // setContentView(R.layout.activity_to_do_list_page_main)

        val ToDoButton = findViewById<Button>(R.id.todo_button)
        ToDoButton.setOnClickListener {
            val intent = Intent(this, ToDoListPageMain::class.java)
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