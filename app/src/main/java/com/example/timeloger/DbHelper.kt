package com.example.timeloger

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DbHelper(context: Context?) : SQLiteOpenHelper(context,DBNAME,null,DBVERSION) {


    override fun onCreate(Db: SQLiteDatabase?) {

        val tasksTable = "CREATE TABLE ${TABLE_TIMER} (" +
                "${TIMER_ID} INTEGER PRIMARY KEY," +
                "${TIMER_TIME} TEXT" +
                ")"

        Db?.execSQL(tasksTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


    @SuppressLint("Recycle")
    fun readTasksData (){
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${TABLE_TIMER} ", arrayOf<String>())
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val time = cursor.getString(1)
            Log.v("Hi from Tasks Table", "$id - $time")
        }
    }
    fun insertTime(std: Timer): Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(TIMER_TIME,std.date)
        val success = db.insert(TABLE_TIMER,null,cv)
        db.close()
        return success
    }
    @SuppressLint("Range")
    fun getAllRecords(): ArrayList<Timer> {
        val stdList : ArrayList<Timer> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_TIMER"
        val db= this.readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int?
        var time : String?
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                time = cursor.getString(cursor.getColumnIndex("time"))

                val std = Timer(id = id,date = time)
                stdList.add(std)
                Log.e("ABC", "${std.id}  ${std.date} ")
            }while (cursor.moveToNext())
        }
        return stdList
    }

    companion object{
        private const val DBNAME = "TimerDb"
        private const val DBVERSION = 1
        const val TABLE_TIMER = "TIMER"
        const val TIMER_ID = "id"
        const val TIMER_TIME = "time"
    }

}