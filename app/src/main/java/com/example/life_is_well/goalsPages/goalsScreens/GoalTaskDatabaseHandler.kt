package com.example.life_is_well.goalsPages.goalsScreens

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.life_is_well.R

//creating the database logic, extending the SQLiteOpenHelper base class
class GoalTaskDatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "GoalTaskDatabase"

        private const val TABLE_CONTACTS = "GoalTaskTable"

        private const val KEY_ID = "_id"
        private const val PARENT_KEY_ID = "_parent_id"
        private const val KEY_TITLE = "taskTitle"
        private const val KEY_DESCRIPTION = "taskDescription"
        private const val KEY_ICON = "taskIcon"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createContactsTable = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + PARENT_KEY_ID + " INTEGER" + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_ICON + " TEXT" + ")")
        db?.execSQL(createContactsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    /**
     * Function to insert data
     */
    fun addGoalTask(goal: GoalTaskItem): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, goal.taskTitle)
        contentValues.put(KEY_DESCRIPTION, goal.taskDescription)

        if (goal.taskIcon == R.drawable.finance) {
            contentValues.put(KEY_ICON, "FINANCE")
        } else if (goal.taskIcon == R.drawable.mental) {
            contentValues.put(KEY_ICON, "MENTAL")
        } else if (goal.taskIcon == R.drawable.health) {
            contentValues.put(KEY_ICON, "HEALTH")
        } else if (goal.taskIcon == R.drawable.plus) {
            contentValues.put(KEY_ICON, "PLUS")
        }

        // Inserting employee details using insert query.
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    //Method to read the records from database in form of ArrayList
    @SuppressLint("Range")
    fun viewGoalCards(): ArrayList<GoalTaskItem> {

        val goalTaskList: ArrayList<GoalTaskItem> = ArrayList()

        // Query to select all the records from the table.
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        // Cursor is used to read the record one by one. Add them to data model class.
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var parentId: Int
        var title: String
        var description: String
        var icon: String
        var iconId: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                parentId = cursor.getInt(cursor.getColumnIndex(PARENT_KEY_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                icon = cursor.getString(cursor.getColumnIndex(KEY_ICON))
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                Log.i("ICON_VIEW", icon)
                if (icon == "FINANCE") {
                    iconId = R.drawable.finance
                } else if (icon == "MENTAL") {
                    iconId = R.drawable.mental
                } else if (icon == "HEALTH") {
                    iconId = R.drawable.health
                } else {
                    iconId = R.drawable.plus
                }
                val task = GoalTaskItem(taskId = id, taskTitle = title, taskDescription = description, taskIcon = iconId, parentGoalId = parentId)
                goalTaskList.add(task)

            } while (cursor.moveToNext())
        }
        cursor.close()
        return goalTaskList
    }

    /**
     * Function to update record
     */
    fun updateGoalCard(task: GoalTaskItem): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, task.taskTitle)
        contentValues.put(PARENT_KEY_ID, task.parentGoalId)
        contentValues.put(KEY_DESCRIPTION, task.taskDescription)
        contentValues.put(KEY_ICON, task.taskIcon)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + task.taskId, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }

    /**
     * Function to delete record
     */
    fun deleteGoalCard(task: GoalTaskItem): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, task.taskId) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + task.taskId, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }
}
