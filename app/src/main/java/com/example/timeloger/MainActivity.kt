package com.example.timeloger

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timeloger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DbHelper
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        dbHelper = DbHelper(applicationContext)
        val broadCast = MyBroadCast()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(broadCast,filter)
    }

}