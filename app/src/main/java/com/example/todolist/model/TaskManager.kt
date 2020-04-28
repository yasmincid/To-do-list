package com.example.todolist.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.CheckBox
import android.widget.TextView
import com.example.todolist.data.DataDbHelper
import java.util.*

class TaskManager(context: Context) {
    val mContext = context
    var admin = DataDbHelper(mContext, "TasksP", null, 1)

    fun addTasks(title: TextView?, description: TextView?): Boolean {
        var bd: SQLiteDatabase = admin.writableDatabase
        var flag = false
        val titleStr = title?.text.toString()
        val descriptionStr = description?.text.toString()
        val date = Calendar.getInstance().time
        if (!titleStr.equals("") && !descriptionStr.equals("")) {
            var values = ContentValues()
            values.put("title", titleStr)
            values.put("description", descriptionStr)
            values.put("date", date.toString())
            bd.insert("Task", null, values)
            bd.close()
            flag = true
        } else {
            flag = false
        }
        return flag
    }

    fun findTask(context: Context, title: TextView): Task {
        var bd: SQLiteDatabase = admin.writableDatabase
        val titleStr = title.text.toString()
        var task: Task = Task("", "")
        if (titleStr.equals("")) {
            var cursor: Cursor =
                bd.rawQuery("select description, date from Task where title = '" + title + "'", null)
            if (cursor.moveToFirst()) {
                var description = cursor.getString(0)
                var date = cursor.getString(1)
                task = Task(titleStr, description)
                bd.close()
                return task
            } else {
                bd.close()
            }
        }
        return task
    }

    fun deleteTask(selected: String, chek: CheckBox): Int {
        var bd: SQLiteDatabase = admin.writableDatabase
        var cant = bd.delete("Task", "title= '" + selected + "'", null)
        bd.close()
        return cant
    }

    fun getAllTasks(): MutableList<Task> {
        var bd: SQLiteDatabase = admin.writableDatabase
        var myList = mutableListOf<Task>()
        var registers: Cursor = bd.rawQuery("select * from Task", null)
        if (registers.moveToFirst()) do {
            val title = registers.getString(1)
            val description = registers.getString(2)
            val task = Task(title, description)
            myList.add(task)
        } while (registers.moveToNext())
        return myList
    }
}