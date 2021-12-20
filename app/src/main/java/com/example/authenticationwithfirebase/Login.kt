package com.example.authenticationwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerText: TextView = findViewById(R.id.registerTextView)
        registerText.setOnClickListener{
            startActivity(Intent(this@Login,Register::class.java))
        }


    }


}