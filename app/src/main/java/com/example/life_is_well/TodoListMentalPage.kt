package com.example.life_is_well


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.databinding.ActivityTodoListMentalPageBinding
import com.example.life_is_well.goalsPages.GoalsPageMain
import com.example.life_is_well.todoPages.DBHandler
import com.example.life_is_well.todoPages.INTENT_TODO_ID
import com.example.life_is_well.todoPages.INTENT_TODO_NAME
import com.example.life_is_well.todoPages.todo.TodoItemActivity
import com.example.life_is_well.todoPages.todo.TodoList
import kotlinx.android.synthetic.main.activity_todo_item.*
import kotlinx.android.synthetic.main.activity_todo_list_mental_page.*
import kotlinx.android.synthetic.main.activity_todo_list_mental_page.rv_dashboard


class TodoListMentalPage : AppCompatActivity() {
    /**Use databasehandler to add the item in the database*/
    lateinit var dbHandler: DBHandler // Database object (line )
    private lateinit var binding: ActivityTodoListMentalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.requestFeature(Window.FEATURE_ACTION_BAR);
        //actionBar?.hide();
        binding = ActivityTodoListMentalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_todo_list_mental_page)

        /** set the action bar as the dashboard toolbar*/
        setSupportActionBar(item_toolbar)
        //setTitle("Dashboard")
        //title = "Dashboard" // then set the title as the "Dashboard"

        dbHandler = DBHandler(this) // Initialize

        /** Set layoutManager for the RecyclerView - to refresh the List for ToDoListPageMain */
        rv_dashboard.layoutManager = LinearLayoutManager(this)// for Line 23

        /** set the add button*/
        fab_dashboard.setOnClickListener {
            val dialog = AlertDialog.Builder(this) // create AlertDialog builder variable
            dialog.setTitle("Add ToDo") // dialog title
            // inflate the layout file (task_dashboard and store it in view variable)
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            // access the EditText from this view
            val toDoName = view.findViewById<EditText>(R.id.ev_todo)

            dialog.setView(view) /** Set view for the dialog*/

            // add the positive button for the dialog
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                // Check the Edit Text if not empty
                if (toDoName.text.isNotEmpty()) {
                    val todoObj = TodoList() // creating ToDo_obj to pass as a parameter
                    todoObj.name = toDoName.text.toString() // set name (entered on EditText))
                    /** Add item into the database using dbHandler (DatabaseHandler)*/
                    dbHandler.addToDo(todoObj) // pass the item as a parameter
                    refreshList() // Call after an item is added
                }
            }
            // Add negative button and pass the "cancel" text
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            }
            dialog.show()
        }

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

       /* val ToDoButton = findViewById<Button>(R.id.button_todo)
        ToDoButton.setOnClickListener {
            val intent = Intent(this, TodoListPageMain::class.java)
            startActivity(intent)
        }
        */
    }

    fun updateToDo(toDo: TodoList){
        val dialog = AlertDialog.Builder(this) // create AlertDialog builder variable
        dialog.setTitle("Update ToDo")
        // inflate the layout file (task_dashboard and store it in view variable)
        val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
        // access the EditText from this view
        val toDoName = view.findViewById<EditText>(R.id.ev_todo)

        toDoName.setText(toDo.name) // set current to do name's text as the editText
        dialog.setView(view) /** Show dialog as the View*/

        // add the positive button for the dialog
        dialog.setPositiveButton("Update") { _: DialogInterface, _: Int ->
            // Check if the Edit Text is not empty
            if (toDoName.text.isNotEmpty()) {
                toDo.name = toDoName.text.toString() // edit the name of the todoList
                /** Update the toDo_List into the database through dbHandler (DatabaseHandler)*/
                dbHandler.updateToDo(toDo)
                refreshList() // Call after an item is added
            }
        }
        // Add negative button and pass the "cancel" text
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
        }
        dialog.show()
    }

    /** When we re-open the application */
    override fun onResume(){
        refreshList()
        super.onResume()
    }

    /** To refresh the List (Line 28) */ // call this after we add an item
    private fun refreshList(){
        // Set the Adapter for the RecyclerView
        // pass the Adapter's context and the getToDos method,
        rv_dashboard.adapter = DashboardAdapter(this, dbHandler.getToDos())
    }

    /** Refresh the RecyclerView after adding the item to the list*/
    // First create the Adapter for the RecyclerView ~ pass the ViewHolder for the Adapter
    class DashboardAdapter(val activity: TodoListMentalPage, val list: MutableList<TodoList>):
        RecyclerView.Adapter<DashboardAdapter.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
            // return the ViewHolder ~ pass the view using LayoutInflater - pass it for the view
            return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_child_dashboard, parent, false))
        }

        // 3 Methods for the Adapter
        override fun getItemCount(): Int {
            // return the RecyclerView Size - parameter = context and ToDo_list in the DashboardAdapter
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // get ViewHolder and the list's position's ItemName
            holder.toDoName.text = list[position].name // and set it as a text for to toDoName

            holder.toDoName.setOnClickListener {  // set onclick listener for todoName textview
                val intent = Intent(activity, TodoItemActivity::class.java) // will open itemActivity when clicking on task name
                intent.putExtra(INTENT_TODO_ID, list[position].id)
                intent.putExtra(INTENT_TODO_NAME, list[position].name)
                activity.startActivity(intent)
            }

            // Set OnClickListener for the menu view
            holder.menu.setOnClickListener{
                // Show pop up menu - by passing the menu and where we want the pop to show (menu)
                val popUp = PopupMenu(activity, holder.menu)
                popUp.inflate(R.menu.dashboard_child) // inflate the child dashboard so it could show the pop in the ImageView
                popUp.setOnMenuItemClickListener { // item click listener
                    when(it.itemId) { // if one of the menu item is clicked
                        R.id.menu_edit -> {
                            // call updateToDo Method from DashboardActivity layout
                            activity.updateToDo(list[position])
                        }
                        R.id.menu_delete -> {
                            // call delete todo_task - by passing the list's ID position
                            activity.dbHandler.deleteToDo(list[position].id)
                            activity.refreshList() // refresh list after deleting an item
                        }
                        R.id.menu_mark_as_completed -> {
                            // call updateToDoItemCompletedStatus - by passing its id and true
                            activity.dbHandler.updateToDoItemCompletedStatus(list[position].id, true)
                        }
                        R.id.menu_reset -> {
                            // call updateToDoItemCompletedStatus - by passing its id and false
                            activity.dbHandler.updateToDoItemCompletedStatus(list[position].id,false)
                        }
                    }
                    true
                }
                popUp.show() // call show method on popUp
            }
        }

        class ViewHolder(v: View): RecyclerView.ViewHolder(v){
            // Add textview obj inside recyclerView
            val toDoName: TextView = v.findViewById(R.id.tv_todo_name)
            // image reference for the menu pop up view
            val menu: ImageView = v.findViewById(R.id.iv_menu)
        }

    }

    fun todoListBackBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
    }

    fun homeBtn(view: View) {
        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)
    }
}