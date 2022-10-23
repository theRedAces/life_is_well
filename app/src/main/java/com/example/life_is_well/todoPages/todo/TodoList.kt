package com.example.life_is_well.todoPages.todo


class TodoList {
    var id: Long = -1 // to store the primary key
    var name = "" // to store task name
    var createdAt = ""
    var items: MutableList<TodoItem> = ArrayList() // to store the task name

}
