package com.example.spinerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {

    var tarea = arrayOf("Tarea 1", "Tarea2", "Tarea3", "Tarea4")
    var hora = arrayOf("12:15", "18:30", "08:15", "20:20")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv1 = findViewById<TextView>(R.id.tv1)
        var list = findViewById<ListView>(R.id.lv1)

        var listaux = mutableListOf<Model>()
        for (i in 0..3) {
            listaux.add(Model(0, tarea[i], "Description", "12/4/20", hora[i]))

        }
        list.adapter = TareaListAdapter(this, R.layout.list_item_tareas, listaux)
        list.setOnItemClickListener { parent, view, position, id ->

            tv1.setText(hora[position])

            Toast.makeText(this, "hora " + hora[position], Toast.LENGTH_SHORT).show()

        }
    }

    fun siguiente(view: View) {
        val int: Intent = Intent(this, NewTask::class.java)
        startActivity(int)

    }


}






