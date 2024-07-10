package com.example.myownapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val redirectButton : Button = findViewById(R.id.redirectBtn)

        redirectButton.setOnClickListener{
            val Intent = Intent(this,CalculatorActivity::class.java)
            startActivity(Intent)
        }
    }
}