package com.example.todolist.model

import java.util.*

class Task(val title: String, val desc: String) {
    var date = Calendar.getInstance().time
}
