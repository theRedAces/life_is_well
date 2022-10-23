package com.example.life_is_well.todoPages.todo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R
import com.example.life_is_well.todoPages.DBHandler
import com.example.life_is_well.todoPages.INTENT_TODO_ID
import com.example.life_is_well.todoPages.INTENT_TODO_NAME
import kotlinx.android.synthetic.main.activity_todo_item.*

class TodoItemActivity : AppCompatActivity() {
    lateinit var dbHandler: DBHandler
    var todoId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_item)
        // calling the action bar

        setSupportActionBar(item_toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set task name as the title for the new task page
        supportActionBar?.title = intent.getStringExtra(INTENT_TODO_NAME)
        todoId = intent.getLongExtra(INTENT_TODO_ID, -1) // get todoId (line 18) and set it as the id for the item (line 48)
        dbHandler = DBHandler(this) // initialize for the fab icon in itemActivity

        rv_item.layoutManager = LinearLayoutManager(this)

        /** set the add button*/
        fab_item.setOnClickListener {
            val dialog = AlertDialog.Builder(this) // create AlertDialog builder variable
            // inflate the layout file (task_dashboard and store it in view variable)
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            // access the EditText from this view
            val toDoName = view.findViewById<EditText>(R.id.ev_todo)

            dialog.setView(view) /** Set view for the dialog*/

            // add the positive button for the dialog
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                // Check the Edit Text if not empty
                if (toDoName.text.isNotEmpty()) {
                    val item = TodoItem() // creating ToDo_obj to pass as a parameter
                    item.itemName = toDoName.text.toString() // set item name (entered on EditText))
                    item.toDoId = todoId
                    item.isCompleted = false
                    /** Add item into the database using dbHandler (DatabaseHandler)*/
                    dbHandler.addToDoItem(item) // pass the item as a parameter
                    refreshList() // Call after an item is added
                }
            }
            // Add negative button and pass the "cancel" text
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            }
            dialog.show()
        }
    }

    override fun onResume() {
        refreshList()
        super.onResume()
    }

    private fun refreshList() {
        rv_item.adapter = ItemAdapter(this,dbHandler, dbHandler.getToDoItems(todoId))
    }

    class ItemAdapter(val context: Context, val dbHandler: DBHandler, val list: MutableList<TodoItem>):
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        // 3 Methods for the Adapter
        // p0 = parent, p1 : Int
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // return the ViewHolder ~ pass the view using LayoutInflater - pass it for the view
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.rv_child_item, parent, false)
            )
        }

        override fun getItemCount(): Int {
            // return the RecyclerView Size - parameter = context and ToDo_list in the DashboardAdapter
            return list.size
        }

        // p0 = parent, p1 : Int
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // get ViewHolder and the list's position's ItemName
            holder.itemName.text = list[position].itemName // and set it as a text for to toDoName
            holder.itemName.isChecked = list[position].isCompleted
            holder.itemName.setOnClickListener {  // set onclick listener for todoName textview
                list[position].isCompleted = !list[position].isCompleted
                dbHandler.updateToDoItem(list[position])
            }
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            // Add the checkbox obj inside recyclerView
            val itemName: CheckBox = v.findViewById(R.id.cb_item)
        }
    }
    /*btnAddToDo.setOnClickListener {
        val todoTitle = etToDoTitle.text.toString()
        if (todoTitle.isNotEmpty()) {
            val todo_ = ToDo_(todo_Title)_T
            todoAdapter.addTodo(todo_)
            etToDoTitle.text.clear()}
    }
    btnDeleteToDo.setOnClickListener {
        todoAdapter.deleteDoneTodos()}
     */

    // Kill current activity when user click on back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item?.itemId == android.R.id.home){ // if home button got selected
            finish() // cleans the activity
            true
        } else
            super.onOptionsItemSelected(item) // click on selected item
    }


}