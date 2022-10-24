package com.example.life_is_well.goalsPages

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
class GoalCardDatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "GoalCardDatabase"

        private const val TABLE_CONTACTS = "GoalsTable"

        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "goalTitle"
        private const val KEY_DESCRIPTION = "goalDescription"
        private const val KEY_ICON = "goalIcon"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createContactsTable = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
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
    fun addGoalCard(goal: GoalItem): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, goal.goalTitle)
        contentValues.put(KEY_DESCRIPTION, goal.goalDescription)

        if (goal.goalIcon == R.drawable.finance) {
            contentValues.put(KEY_ICON, "FINANCE")
        } else if (goal.goalIcon == R.drawable.mental) {
            contentValues.put(KEY_ICON, "MENTAL")
        } else if (goal.goalIcon == R.drawable.health) {
            contentValues.put(KEY_ICON, "HEALTH")
        } else if (goal.goalIcon == R.drawable.plus) {
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
    fun viewGoalCards(): ArrayList<GoalItem> {

        val goalList: ArrayList<GoalItem> = ArrayList()

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
        var title: String
        var description: String
        var icon: String
        var iconId: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
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
                val goal = GoalItem(goalId = id, goalTitle = title, goalDescription = description, goalIcon = iconId)
                goalList.add(goal)

            } while (cursor.moveToNext())
        }
        cursor.close()
        return goalList
    }

    /**
     * Function to update record
     */
    fun updateGoalCard(goal: GoalItem): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, goal.goalTitle)
        contentValues.put(KEY_DESCRIPTION, goal.goalDescription)
        contentValues.put(KEY_ICON, goal.goalIcon)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + goal.goalId, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }

    /**
     * Function to delete record
     */
    fun deleteGoalCard(goal: GoalItem): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, goal.goalId) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + goal.goalId, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }
}
