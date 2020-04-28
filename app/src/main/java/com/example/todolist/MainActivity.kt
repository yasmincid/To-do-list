package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.model.Task
import com.example.todolist.model.TaskManager


class MainActivity : AppCompatActivity() {

    var listaux = mutableListOf<Task>()
    var selectedTask = ""
    var taskManager = TaskManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = findViewById<ListView>(R.id.lvTask)
        listaux = taskManager.getAllTasks()
        list.adapter = TaskListAdapter(this, R.layout.list_item_tareas, listaux)
        list.setOnItemClickListener { parent, view, position, id ->
            selectedTask = listaux.get(position).title
            Toast.makeText(this, selectedTask, Toast.LENGTH_SHORT).show()
        }
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
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun add(view: View) {
        val int: Intent = Intent(this, NewTask::class.java)
        startActivity(int)
    }

    fun deletedTask(view: View) {
        val chek = findViewById<CheckBox>(R.id.check1)
        taskManager.deleteTask(selectedTask, chek)
    }
}










