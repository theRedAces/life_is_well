package com.example.life_is_well.todo

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.MainActivity
import com.example.life_is_well.R
import com.example.life_is_well.TodoMentalPage
import com.example.life_is_well.databinding.ActivityTodoListPageMainBinding
import kotlinx.android.synthetic.main.activity_todo_list_page_main.*

class ToDoListPageMain : AppCompatActivity() {
    private lateinit var binding: ActivityTodoListPageMainBinding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoListPageMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_todo_list_page_main)

        todoAdapter = TodoAdapter(mutableListOf())

        rv_Recycler_View.adapter = todoAdapter
        rv_Recycler_View.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {
            val todoTitle = etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDoTitle.text.clear()
            }
        }
        btnDeleteToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }

    fun ToDoListHomeBtn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun ToDoListBackBtn(view: View) {
        val intent = Intent(this, TodoMentalPage::class.java)
        startActivity(intent)
    }
}
