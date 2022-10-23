package com.example.life_is_well.todoPages

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.life_is_well.todoPages.todo.TodoItem
import com.example.life_is_well.todoPages.todo.TodoList

// this DataBase class will execute the table values from Content class
class DBHandler(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createToDoTable = "CREATE TABLE $TABLE_TODO (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_NAME varchar);"

        val createToDoItemTable = "CREATE TABLE $TABLE_TODO_ITEM (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_TODO_ID integer," +
                "$COL_ITEM_NAME varchar," +
                "$COL_IS_COMPLETED integer);"

        db.execSQL(createToDoTable)
        db.execSQL(createToDoItemTable)
    }


    // Inserting the task from ToDO_class using DataBaseHandler
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    // Inserting the task from DashboardActivity using DataBaseHandler
    // Adding the task by passing the ToDo_obj as a parameter
    fun addToDo(toDo: TodoList): Boolean {
        val db = writableDatabase // insert the data with writableDatabase
        val cv = ContentValues() // create a content value
        cv.put(COL_NAME, toDo.name) // add the column name inside it
        val result = db.insert(TABLE_TODO, null, cv) // call insert by passing the table name , null and content values
        return result != (-1).toLong() // return type = boolean to give the insertion status to the calling class
        // return = true when result is not equal to 1, otherwise will return false
    }

    fun updateToDo(toDo: TodoList){
        val db = writableDatabase // insert the data with writableDatabase
        val cv = ContentValues() // create a content value
        cv.put(COL_NAME, toDo.name) // add the column name inside it
        // call update method to update the task where the column_ID is = to toDo_ID
        db.update(TABLE_TODO, cv, "$COL_ID=?", arrayOf(toDo.id.toString()))
    }

    // deletes toDoItem
    fun deleteToDo(todoId: Long){
        val db = writableDatabase // get writableDatabase to delete the record
        // pass 3 arguments to delete item and the toDoList - the table, and where the column Id is = todoId
        db.delete(TABLE_TODO_ITEM, "$COL_TODO_ID=?", arrayOf(todoId.toString()))
        db.delete(TABLE_TODO, "$COL_ID=?", arrayOf(todoId.toString()))
    }

    // next, get the list of items
    fun getToDos(): MutableList<TodoList> {
        val result: MutableList<TodoList> = ArrayList() // store ToDo_items in a list and return in
        val db = readableDatabase // read the data with readableDatabase

        // use query method to get all the records from the TABLE_TODO content
        val queryResult = db.rawQuery("SELECT * from $TABLE_TODO", null)

        // read data from the query using Do while loop
        if (queryResult.moveToFirst()) // if true
        {
            do // then we have data inside the table
            {
                // to do so, read the data then create the ToDO_List
                val todo = TodoList() // create todo_object to store the data in the list
                // Use queryResult to get the values for the ID and its name
                // store each in ID and name into the todo_obj variable
                todo.id =
                    queryResult.getLong(queryResult.getColumnIndex(COL_ID)) // pass column index (getColumnIndex) to get the ID
                todo.name =
                    queryResult.getString(queryResult.getColumnIndex(COL_NAME)) // pass column name (getColumnName) to get the name
                result.add(todo) // then add the obj inside the toDo_List
            } while (queryResult.moveToNext())
        }
        queryResult.close() // close the query after getting the content
        return result
    }

    // mark toDoItem as complete - pass the todoId and its completed status as true or false
    // use this for reset as well
    fun updateToDoItemCompletedStatus(todoId: Long, icCompleted: Boolean){
        val db = writableDatabase
        // get all the items from getToDoItems - copy code
        // rawQuery method gives the records from TODO_ITEM TABLE
        // where toDoID = the ID we're passing
        val queryResult = db.rawQuery("SELECT * FROM $TABLE_TODO_ITEM WHERE $COL_TODO_ID=$todoId", null)

        // use do while loop to read all the data from queryResult
        // if first line is true/not empty
        if (queryResult.moveToFirst()) {
            do {
                // read the data and create a to do list
                val item = TodoItem() // to do object to store data
                // Use getLong to get the Id from queryResult - pass the column index (which we don't know)
                item.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                // Use getColumnIndex to get ID's ColumnIndex
                item.toDoId = queryResult.getLong(queryResult.getColumnIndex(COL_TODO_ID))
                // Get task name from queryResult by using getString method
                item.itemName = queryResult.getString(queryResult.getColumnIndex(COL_ITEM_NAME))
                item.isCompleted = icCompleted // change completed status to true
                updateToDoItem(item) // update the item inside the list
            } while (queryResult.moveToNext())
        }

        queryResult.close() // close the tack after we get the result
    }

    // for adding the subtask
    // Inserting the task from DashboardActivity using DataBaseHandler
    // Adding the task by passing the ToDo_obj as a parameter
    fun addToDoItem(item: TodoItem): Boolean {
        val db = writableDatabase // insert the data with writableDatabase
        val cv = ContentValues() // create a content value
        cv.put(COL_ITEM_NAME, item.itemName) // add the item name and to doId inside it
        cv.put(COL_TODO_ID, item.toDoId)

        if (item.isCompleted) // if is completed is true
            cv.put(COL_IS_COMPLETED, true)
        else
            cv.put(COL_IS_COMPLETED, false)

        val result = db.insert(TABLE_TODO_ITEM, null, cv) // call insert by passing the table item , null and content values
        return result != (-1).toLong() // return type = boolean to give the insertion status to the calling class
        // return = true when result is not equal to 1, otherwise will return false
    }

    // for adding the subtask
    // Inserting the task from DashboardActivity using DataBaseHandler
    // Adding the task by passing the ToDoItem as a parameter
    fun updateToDoItem(item: TodoItem) {
        val db = writableDatabase // we need writable database and content values variables
        val cv = ContentValues() // create a content value
        cv.put(COL_ITEM_NAME, item.itemName) // add the item name and to doId inside it
        cv.put(COL_TODO_ID, item.toDoId)
        cv.put(COL_IS_COMPLETED, item.isCompleted)

        // call update (pass table name and its content values, pass whereClass as ColumnID, and the String Array as item id)
        db.update(TABLE_TODO_ITEM, cv, "$COL_ID=?", arrayOf(item.id.toString()))
    }

    // method for getting the subtask
    // add todoID as a parameter - MutableList = return type
    fun getToDoItems(todoId: Long): MutableList<TodoItem> {
        // create a MutableList to store the todoItem
        // & assign it to an ArrayList
        val result: MutableList<TodoItem> = ArrayList()

        // Read the data (from database) and insert it into the list
        val db = readableDatabase

        // rawQuery method gives the records from TODO_ITEM TABLE
        // where toDoID = the ID we're passing
        val queryResult = db.rawQuery("SELECT * FROM $TABLE_TODO_ITEM WHERE $COL_TODO_ID=$todoId", null)

        //  use do while loop to read all the data from queryResult
        if (queryResult.moveToFirst()) // if first line is true/not empty
        {
            do {
                // read the data and create a to do list
                val item = TodoItem() // to do object to store data
                // Use getLong to get the Id from queryResult - pass the column index (which we don't know)
                item.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                // Use getColumnIndex to get ID's ColumnIndex
                item.toDoId = queryResult.getLong(queryResult.getColumnIndex(COL_TODO_ID))
                // Get task name from queryResult by using getString method
                item.itemName = queryResult.getString(queryResult.getColumnIndex(COL_ITEM_NAME))

                // Use getInt (int = for boolean) method to get the integer value stored in the database
                // and compared it to 1 ~ if stored value is 1 then true, else false
                item.isCompleted = queryResult.getInt(queryResult.getColumnIndexOrThrow(COL_IS_COMPLETED)) == 1
                result.add(item) // add the item inside the list
            } while (queryResult.moveToNext())
        }

        queryResult.close() // close the tack after we get the result
        return result
    }

}