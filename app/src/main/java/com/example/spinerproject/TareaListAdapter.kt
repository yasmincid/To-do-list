package com.example.spinerproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TareaListAdapter(var mCtx: Context, var resource: Int, var items: List<Model>) :
    ArrayAdapter<Model>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)
        val tv1: TextView = view.findViewById(R.id.title)
        val tv2: TextView = view.findViewById(R.id.descrip)
        var iTems:Model = items[position]
        tv1.text=iTems.title
        tv2.text=iTems.desc
        return view
    }
}