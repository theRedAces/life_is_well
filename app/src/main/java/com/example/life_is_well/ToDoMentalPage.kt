package com.example.life_is_well


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.life_is_well.databinding.ActivityToDoMentalPageBinding
import com.example.life_is_well.todo.ToDoListPageMain


class TodoMentalPage : AppCompatActivity() {
   private lateinit var binding: ActivityToDoMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToDoMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_to_do_list_page_main)

        val ToDoButton = findViewById<Button>(R.id.ToDo_Button)
        ToDoButton.setOnClickListener {
            val intent = Intent(this, ToDoListPageMain::class.java)
            startActivity(intent)
        }
    }

        fun backBtn(view: View) {
            val intent = Intent(this, MentalPageMain::class.java)
            startActivity(intent)
        }

        fun homeBtn(view: View) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }
}