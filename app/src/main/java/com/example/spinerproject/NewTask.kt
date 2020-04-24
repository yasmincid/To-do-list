package com.example.spinerproject

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import data.DataDbHelper

class NewTask : AppCompatActivity() {

    private var titleTv: TextView? = null
    private var descriptionMl: TextView? = null
    private var dateTv: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        titleTv = findViewById<TextView>(R.id.titleNewTask)
        descriptionMl = findViewById<TextView>(R.id.descripNewTask)
        dateTv = findViewById<TextView>(R.id.dateNewTask)

    }

    fun back(view: View) {
        val int: Intent = Intent(this, MainActivity::class.java)
        startActivity(int)

    }

    fun addDatos(view: View) {
        val myDataBase = DataDbHelper(this, "TasksP", null, 1)
        val data: SQLiteDatabase = myDataBase.writableDatabase
        val titleStr = titleTv?.text.toString()
        val descriptionStr = descriptionMl?.text.toString()
        val date = dateTv?.text.toString()
        val x = "select description, date from Task where title = " + titleStr.toString()
        Log.d("hj", x)
        if (titleStr.isNotEmpty() && descriptionStr.isNotEmpty()) {
            var values = ContentValues()

            values.put("title", titleStr)
            values.put("description", descriptionStr)
            values.put("date", date)
            data.insert("Task", null, values)
            data.close()
            titleTv?.setText("")
            descriptionMl?.setText("")
            dateTv?.setText("")
            Toast.makeText(this, "ADICIONADO", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun findTask(view: View) {
        val myDatabase = DataDbHelper(this, "TasksP", null, 1)
        val data: SQLiteDatabase = myDatabase.writableDatabase
        val title: String = titleTv?.text.toString()
        if (title.isNotEmpty()) {

            var cursor: Cursor = data.rawQuery("select description, date from Task where title = '" + title + "'", null)
            if (cursor.moveToFirst()) {
                descriptionMl?.setText(cursor.getString(0))
                dateTv?.setText(cursor.getString(1))
                data.close()
            } else {
                Toast.makeText(this, "There is not a task with that title", Toast.LENGTH_SHORT).show()
                data.close()
            }

        } else {
            Toast.makeText(this, "you should introduce a title", Toast.LENGTH_SHORT).show()
        }

    }


}

