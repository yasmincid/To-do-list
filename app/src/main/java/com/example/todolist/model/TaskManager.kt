package com.example.todolist.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.todolist.data.DataDbHelper
import java.util.*

class TaskManager {

    fun addTasks(context: Context, title: TextView?, description: TextView?) {
        val myDataBase = DataDbHelper(context, "TasksP", null, 1)
        val data: SQLiteDatabase = myDataBase.writableDatabase
        val titleStr = title?.text.toString()
        val descriptionStr = description?.text.toString()
        val date = Calendar.getInstance().time
        if (titleStr.isNotEmpty() && descriptionStr.isNotEmpty()) {
            var values = ContentValues()

            values.put("title", titleStr)
            values.put("description", descriptionStr)
            values.put("date", date.toString())
            data.insert("Task", null, values)
            data.close()
            title?.setText("")
            description?.setText("")
            Toast.makeText(context, "ADICIONADO", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    fun findTask(context: Context, title: TextView): Task {
        val titleStr = title.text.toString()
        val myDatabase = DataDbHelper(context, "TasksP", null, 1)
        val data: SQLiteDatabase = myDatabase.writableDatabase
        var task: Task = Task("", "")
        if (titleStr.isNotEmpty()) {

            var cursor: Cursor =
                data.rawQuery("select description, date from Task where title = '" + title + "'", null)
            if (cursor.moveToFirst()) {

                var description = cursor.getString(0)
                var date = cursor.getString(1)
                task = Task(titleStr, description)
                data.close()
                return task
            } else {
                Toast.makeText(context, "There is not a task with that title", Toast.LENGTH_SHORT).show()
                data.close()


            }
        }
        return task

    }

    fun deleteTask(context: Context, selected: String, chek: CheckBox) {
        val admin = DataDbHelper(context, "TasksP", null, 1)
        val bd: SQLiteDatabase = admin.writableDatabase

        var cant = bd.delete("Task", "title= '" + selected + "'", null)
        bd.close()

        if (cant == 1) {
            chek.setText("")
            Toast.makeText(context, "The task was delete", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "The task doesn't exits" + selected, Toast.LENGTH_SHORT).show()
        }

    }

    fun getAllTasks(context: Context): MutableList<Task> {
        val myDatabase = DataDbHelper(context, "TasksP", null, 1)
        val data: SQLiteDatabase = myDatabase.writableDatabase
        var myList = mutableListOf<Task>()
        var registers: Cursor = data.rawQuery("select * from Task", null)
        if (registers.moveToFirst()) do {
            val title = registers.getString(1)
            val description = registers.getString(2)
            val task = Task(title, description)
            myList.add(task)
        } while (registers.moveToNext())
        return myList
    }
}