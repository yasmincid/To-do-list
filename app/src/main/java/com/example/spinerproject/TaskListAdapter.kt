package com.example.spinerproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import model.Task

class TaskListAdapter(var mCtx: Context, var resource: Int, var items: MutableList<Task>) :
    ArrayAdapter<Task>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)
        var Chb1: CheckBox = view.findViewById(R.id.check1)
        var tv1: TextView = view.findViewById(R.id.title)
        var tv2: TextView = view.findViewById(R.id.description)
        var iTems = items[position]
        tv1.text = iTems.title
        tv2.text = iTems.desc
        return view
    }
}