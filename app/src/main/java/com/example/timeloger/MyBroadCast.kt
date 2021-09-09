package com.example.timeloger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import java.util.*

class MyBroadCast: BroadcastReceiver() {
    private lateinit var dbHelper: DbHelper
    override fun onReceive(p0: Context?, p1: Intent?) {
        dbHelper = DbHelper(p0)
        when(p1?.action){
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (p1.extras?.getBoolean("state") == true){
                    addTime()
                    Toast.makeText(p0,"Hello from AIRPLANE",Toast.LENGTH_LONG).show()
                    Log.i("AAA", SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date()))
                    dbHelper.getAllRecords()
                }
            }
        }
    }

    private fun addTime() {
        val id = 1
        val std = Timer(id+1,SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date()))
        val add = dbHelper.insertTime(std)
        if(add > -1){
            Log.i("BBB","Success")
        }
    }
}