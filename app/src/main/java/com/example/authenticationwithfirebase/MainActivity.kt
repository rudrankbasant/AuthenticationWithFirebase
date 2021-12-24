package com.example.authenticationwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailID: TextView = findViewById(R.id.textViewEmailID)
        val logout: Button = findViewById(R.id.logoutButton)
        val emailid = intent.getStringExtra("email_id")



        emailID.text= "EMAIL ID: $emailid"

        logout.setOnClickListener{

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}