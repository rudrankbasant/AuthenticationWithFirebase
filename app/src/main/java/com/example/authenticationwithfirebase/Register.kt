package com.example.authenticationwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginText: TextView = findViewById(R.id.loginTextView)
        loginText.setOnClickListener{
            startActivity(Intent(this@Register,Login::class.java))
        }
    }
}