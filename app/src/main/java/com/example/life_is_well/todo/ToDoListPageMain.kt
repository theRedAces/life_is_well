package com.example.life_is_well.todo

import android.content.Context
import android.content.DialogInterface
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
import com.example.life_is_well.R
import kotlinx.android.synthetic.main.activity_todo_list_page_main.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ToDoListPageMain : AppCompatActivity() {

    lateinit var dbHandler: DatabaseHandler // Database object (line 28)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_page_main)
        setSupportActionBar(Toolbar_title_)
        title = "To Do List"
        dbHandler = DatabaseHandler(this)
        layout_Recycler_View.layoutManager = LinearLayoutManager(this)

        // Set layoutManager for the RecyclerView - for ToDoListPageMain to refresh the List
        layout_Recycler_View.layoutManager = LinearLayoutManager(this) // for Line 23

        // Add the task when you click on the fab_icon (with the task_dashboard layout)
        fab_icon.setOnClickListener {
            val dialog = AlertDialog.Builder(this) // create AlertDialog builder variable
            // inflate the layout file (task_dashboard and store it in view variable)
            val view = layoutInflater.inflate(R.layout.task_dashboard, null)

            // access the EditText from this view
            val toDoName = view.findViewById<EditText>(R.id.ev_todo)

            dialog.setView(view) // Set view for the dialog

            // add the positive button for the dialog
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                // Check the Edit Text if not empty
                if (toDoName.text.isNotEmpty()) {
                    val toDo = ToDo() // pass toDo_obj by creating ToDo_obj
                    toDo.name =
                        toDoName.text.toString() //set the obj name (entered on the Edit text)
                    //Then add item into the database using dbHandler (DatabaseHandler)
                    dbHandler.addToDo(toDo) // pass the toDo_obj as a parameter for the addToDo method in DatabaseHandler class
                    refreshList() // Call after an item is added
                }
            }
            // Add negative button and pass the "cancel" text
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            }
            dialog.show()
        }
    }


    override fun onResume() // Will show the list when you open the application
    {
        refreshList()
        super.onResume()
    }

    // To refresh the List (Line 31)
    private fun refreshList(){
        // Set the Adapter for the RecyclerView - pass the Adapter's context and the getToDos method,
        layout_Recycler_View.adapter = DashboardAdapter(this, dbHandler.getToDos())
    }


    // Then refresh the Recycler View after adding the item
    // First create the Adapter for the RecyclerView ~ pass the ViewHolder for the Adapter
    class DashboardAdapter(val context: Context, val list: MutableList<ToDo>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>()
    {
        // 3 Methods for the Adapter
                                        // p0 = parent, p1 : Int
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // return the ViewHolder ~ pass the view using LayoutInflater - pass it for the view
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.child_to_do_page,parent, false))
        }

                                        // p0 = parent, p1 : Int
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // get ViewHolder and get list's position's ItemName and set it as a text for to toDoName
            holder.toDoName.text = list[position].name
        }

        override fun getItemCount(): Int {
            // return the RecyclerView Size - parameter = context and ToDo_list in the DashboardAdapter
            return list.size
        }

        // Create viewHolder inside itself - pass view as a parameter
        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            // we need a child layout for the Recycler View (tv_todo_name)
            // Add the TextView inside the ViewHolder
            val toDoName: TextView = v.findViewById(R.id.tv_todo_name)
        }
    }
}

