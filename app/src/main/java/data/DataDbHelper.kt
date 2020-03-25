package data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory
import com.example.spinerproject.Model

class DataDbHelper(context: Context, namebd: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, namebd, null, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            """CREATE TABLE ${Tables.Models.table}(${Tables.Models.id}INTEGER PRIMARY KEY AUTOINCREMENT,${Tables.Models.title}TEXT NOT NULL, ${Tables.Models.description}TEXT NOT NULL, ${Tables.Models.date}TEXT NOT NULL,${Tables.Models.time}TEXT NOT NULL)"""
        )
        // db!!.execSQL("create table Tareas(codigo integer primary key autoincrement, title text  )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    fun insert(tarea: List<Model>) {
//        values.put(Tables.Models.title, tarea[0].title)
//        values.put(Tables.Models.description, tarea[0].desc)
//        values.put(Tables.Models.date, tarea[0].date)
//        values.put(Tables.Models.time, tarea[0].time)
//        db.insert(Tables.Models.table, null, values)
//}
}