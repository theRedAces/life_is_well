package com.example.life_is_well.todoPages.todo

// implementing the values from ToDo_class
class TodoItem() // for each item in the ToDo_class
{
    var id : Long = -1 // to store the primary key
    var toDoId : Long = -1  // to store the parent task ID
    var itemName = "" // to store subtask name
    var isCompleted = false // to store the status for each item
}