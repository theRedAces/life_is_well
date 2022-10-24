package com.example.life_is_well.goalsPages

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.MainActivity
import com.example.life_is_well.ProfilePageMain
import com.example.life_is_well.R
import com.example.life_is_well.SettingsPageMain
import com.example.life_is_well.databinding.GoalsListPageMainBinding
import com.example.life_is_well.databinding.GoalsNewCardDialogBinding
import com.example.life_is_well.goalsPages.goalsScreens.GoalTaskPageMain
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.goals_list_page_main.*
import kotlinx.android.synthetic.main.goals_new_card_dialog.*
import kotlinx.android.synthetic.main.goals_update_dialog.*

class GoalsPageMain : AppCompatActivity() {
    private lateinit var binding: GoalsListPageMainBinding
    private lateinit var binding1: GoalsNewCardDialogBinding
    private lateinit var newGoalRecyclerView: RecyclerView
    private var newGoalButton: FloatingActionButton? = null

    private lateinit var goalsList: ArrayList<GoalItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GoalsListPageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGoalRecyclerView = findViewById(R.id.goal_recycler_view)

        newGoalButton = findViewById(R.id.new_goal_button)

        newGoalRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
        newGoalRecyclerView.setHasFixedSize(true)
        newGoalButton?.setOnClickListener { addRecordDialog() }

        goals_bottom_nav.setOnItemSelectedListener {
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
        goalsList = arrayListOf()
        setupListOfDataIntoRecyclerView()
    }

    /**
     * Function is used to get the Items List which is added in the database table.
     */
    private fun getItemsList(): ArrayList<GoalItem> {
        //creating the instance of DatabaseHandler class
        val databaseHandler = GoalCardDatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records

        return databaseHandler.viewGoalCards()
    }

    private fun goalCardOnClick(position: Int) : Unit {
        val intent = Intent(this, GoalTaskPageMain::class.java)
        startActivity(intent)
        Toast.makeText(this, goalsList[position].goalTitle, Toast.LENGTH_SHORT).show()
    }


    /**
     * Function is used to show the list on UI of inserted data.
     */
    private fun setupListOfDataIntoRecyclerView() {
            // Set the LayoutManager that this RecyclerView will use.
        goal_recycler_view.layoutManager = GridLayoutManager(this, 2)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = GoalAdapter(this, getItemsList()) { position: Int ->
                goalCardOnClick(
                    position
                )
            }
            // adapter instance is set to the recyclerview to inflate the items.
        goal_recycler_view.adapter = itemAdapter
    }

    /**
     * Method is used to show the custom update dialog.
     */
    fun addRecordDialog() {
        val addGoalDialog = Dialog(this, R.style.Theme_Goal_Dialog)
        addGoalDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        addGoalDialog.setContentView(R.layout.goals_new_card_dialog)
        binding1 = GoalsNewCardDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databaseHandler = GoalCardDatabaseHandler(this)
        addGoalDialog.add_goal_card_submit.setOnClickListener {

            val title = addGoalDialog.add_goal_title.text.toString()
            val description = addGoalDialog.add_goal_description.text.toString()
            val icon = addGoalDialog.add_goal_icon.text.toString()
            Log.i("ICON_TEXT", icon)
            var iconId: Int
            if (icon == "Finance") {
                iconId = R.drawable.finance
            } else if (icon == "Mental") {
                iconId = R.drawable.mental
            } else if (icon == "Physical") {
                iconId = R.drawable.health
            } else {
                iconId = R.drawable.plus
            }
            Log.i("FINANCE", R.drawable.finance.toString())
            Log.i("MENTAL", R.drawable.mental.toString())
            Log.i("PHYSICAL", R.drawable.health.toString())
            Log.i("PLUS", R.drawable.plus.toString())
            Log.i("ICON_SET", iconId.toString())

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val status =
                    databaseHandler.addGoalCard(GoalItem(title, iconId, description, 0))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Goal Added", Toast.LENGTH_LONG).show()

                    setupListOfDataIntoRecyclerView()

                    addGoalDialog.dismiss() // Dialog will be dismissed
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Fields cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        addGoalDialog.add_goal_cancel_submit.setOnClickListener {
            setContentView(binding.root)
            addGoalDialog.dismiss()
        }

        //Start the dialog and display it on screen.
        addGoalDialog.show()
    }

    /**
     * Method is used to show the custom update dialog.
     */
    fun updateRecordDialog(goalItemClass: GoalItem) {
        val updateDialog = Dialog(this, R.style.Theme_Goal_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.goals_update_dialog)

        updateDialog.update_goal_title.setText(goalItemClass.goalTitle)
        updateDialog.update_goal_description.setText(goalItemClass.goalDescription)
        // This will need a bit of leg work to get an image from dropdown selection.
        updateDialog.update_goal_icon.setText(goalItemClass.goalIcon)

        updateDialog.update_goal_card_submit.setOnClickListener {

            val title = updateDialog.update_goal_title.text.toString()
            val description = updateDialog.update_goal_description.text.toString()
            val icon = updateDialog.update_goal_icon.id

            val databaseHandler = GoalCardDatabaseHandler(this)

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val status =
                    databaseHandler.updateGoalCard(GoalItem(title, icon, description, goalItemClass.goalId))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                    setupListOfDataIntoRecyclerView()

                    updateDialog.dismiss() // Dialog will be dismissed
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Name or Email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        updateDialog.goal_cancel_submit.setOnClickListener {
            updateDialog.dismiss()
        }
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    /**
     * Method is used to show the delete alert dialog.
     */
    fun deleteRecordAlertDialog(goalItemClass: GoalItem) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Goal")
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete ${goalItemClass.goalTitle}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, _ ->

            //creating the instance of DatabaseHandler class
            val databaseHandler = GoalCardDatabaseHandler(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = databaseHandler.deleteGoalCard(GoalItem("", 0, "", goalItemClass.goalId))
            if (status > -1) {
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()
                setupListOfDataIntoRecyclerView()
            }
            dialogInterface.dismiss()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }
}
