package data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataDbHelper(context: Context, namebd: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, namebd, null, version) {


    override fun onCreate(TasksP: SQLiteDatabase?) {

        TasksP?.execSQL(
            "create table Task ( id integer primary key autoincrement, title text, description text, date text)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}