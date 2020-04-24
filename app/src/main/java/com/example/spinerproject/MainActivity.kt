package com.example.spinerproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import data.DataDbHelper
import model.Task


class MainActivity : AppCompatActivity() {

    var listaux = mutableListOf<Task>()
    var chek: CheckBox? = null
    var selectedTask: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = findViewById<ListView>(R.id.lv1)
        listaux = llenarList()
        list.adapter = TaskListAdapter(this, R.layout.list_item_tareas, listaux)

        list.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this, list.get(position).toString(), Toast.LENGTH_SHORT).show()
            }
            if (position == 1) {
                Toast.makeText(this, list.get(position).toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun llenarList(): MutableList<Task> {
        val myDatabase = DataDbHelper(this, "TasksP", null, 1)
        val data: SQLiteDatabase = myDatabase.writableDatabase
        var myList = mutableListOf<Task>()
        var registers: Cursor = data.rawQuery("select * from Task", null)
        if (registers.moveToFirst()) do {
            val title = registers.getString(1)
            val description = registers.getString(2)
            val date = registers.getString(3)
            val task = Task(title, description, date)
            myList.add(task)
        } while (registers.moveToNext())


        return myList
    }


    //show and hide menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.taskmenu, menu)
        return true
    }

    //seleted option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.settings) {
            val int: Intent = Intent(this, Settings::class.java)
            startActivity(int)
        }
        if (id == R.id.newTask) {
            val int: Intent = Intent(this, NewTask::class.java)
            startActivity(int)

        }
        if (id == R.id.share) {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.app_bar_search) {
            var title = findViewById<SearchView>(R.id.app_bar_search).toString()

            val int: Intent = Intent(this, NewTask::class.java)
            int.putExtra("title", findTask(title).title)
            int.putExtra("Descrip", findTask(title).desc)
            int.putExtra("date", findTask(title).date)
            var titlet = findViewById<TextView>(R.id.titleNewTask)
            titlet.setText(findTask(title).title)
            startActivity(int)
        }
        return super.onOptionsItemSelected(item)
    }

    fun findTask(title: String): Task {
        val myDatabase = DataDbHelper(this, "TasksP", null, 1)
        val data: SQLiteDatabase = myDatabase.writableDatabase
        var task: Task = Task("", "", "")
        if (title.isNotEmpty()) {

            var cursor: Cursor =
                data.rawQuery("select description, date from Task where title = '" + title + "'", null)
            if (cursor.moveToFirst()) {

                var description = cursor.getString(0)
                var date = cursor.getString(1)
                task = Task(title, description, date)
                data.close()
                return task
            } else {
                Toast.makeText(this, "There is not a task with that title", Toast.LENGTH_SHORT).show()
                data.close()


            }
        }
        return task

    }

    fun deleteTask(view: View) {
        val admin = DataDbHelper(this, "TasksP", null, 1)
        val bd: SQLiteDatabase = admin.writableDatabase

        chek = findViewById<CheckBox>(R.id.check1)
        selectedTask = findViewById(R.id.title)
        val titleStr = selectedTask?.text.toString()
        var cant = bd.delete("Task", "title= '" + titleStr + "'", null)
        bd.close()

        if (cant == 1) {
            Toast.makeText(this, "The task was delete", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "The task doesn't exits" + titleStr, Toast.LENGTH_SHORT).show()
        }


    }
}










