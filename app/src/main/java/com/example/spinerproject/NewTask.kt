package com.example.spinerproject

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import data.DataDbHelper

class NewTask : AppCompatActivity() {
    private var titleStr = ""
    private var descriptionStr = ""
    private var timeStr = ""
    private var dateStr = ""
    private var listBd: MutableList<Model> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)


    }

    fun back(view: View) {
        val int: Intent = Intent(this, MainActivity::class.java)
        startActivity(int)

    }

    fun addDatos(view: View) {
        val myDataBase: DataDbHelper = DataDbHelper(this, "Tareas", null, 1)
        val data: SQLiteDatabase = myDataBase.writableDatabase
        var titleTv = findViewById<TextView>(R.id.titleNewTask)
        var descriptionMl = findViewById<TextView>(R.id.descripNewTask)
        var timeTv = findViewById<TextView>(R.id.timeNewTask)
        var dateTv = findViewById<TextView>(R.id.dateNewTask)
        timeStr = timeTv.text.toString()
        titleStr = titleTv.text.toString()
        descriptionStr = descriptionMl.text.toString()
        dateStr = dateTv.text.toString()
        if (titleStr.isNotEmpty() && descriptionStr.isNotEmpty() && dateStr.isNotEmpty() && timeStr.isNotEmpty()) {
            var values: ContentValues = ContentValues()
            values.put("Title", titleStr)
            values.put("Description", descriptionStr)
            values.put("Date", dateStr)
            values.put("Time", timeStr)
            data.insert("Tareas", null, values)
            data.close()
            titleTv.setText("")
            descriptionMl.setText("")
            dateTv.setText("")
            timeTv.setText("")
            Toast.makeText(this, "ADICIONADO", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }


    }

}
