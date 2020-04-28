package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.model.TaskManager

class NewTask : AppCompatActivity() {

    private var titleTv: TextView? = null
    private var descriptionMl: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        titleTv = findViewById<TextView>(R.id.titleNewTask)
        descriptionMl = findViewById<TextView>(R.id.descripNewTask)
    }

    fun back() {
        val int: Intent = Intent(this, MainActivity::class.java)
        startActivity(int)
    }

    //show and hide menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.addmenu, menu)
        return true
    }

    //seleted option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.back) {
            val int: Intent = Intent(this, MainActivity::class.java)
            startActivity(int)
        }
        if (id == R.id.save) {
            val taskManager = TaskManager(this)
            if (taskManager.addTasks(titleTv, descriptionMl) == true) {
                titleTv?.setText("")
                descriptionMl?.setText("")
                Toast.makeText(this, "The task was add", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "You should introdoce all dates", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

