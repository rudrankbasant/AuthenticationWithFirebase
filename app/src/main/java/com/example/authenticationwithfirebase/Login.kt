package com.example.authenticationwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerText: TextView = findViewById(R.id.registerTextView)
        val loginButton: Button = findViewById(R.id.buttonLogin)
        val loginEmail: EditText = findViewById(R.id.editTextLoginEmail)
        val loginPassword: EditText = findViewById(R.id.editTextLoginPassword)


        registerText.setOnClickListener{
            startActivity(Intent(this@Login,Register::class.java))
        }

        loginButton.setOnClickListener{
            val emailID:String=loginEmail.text.toString().trim{it <=' '}
            val pass:String=loginPassword.text.toString().trim{it <=' '}
             when{
                 TextUtils.isEmpty(emailID)->{
                     Toast.makeText(this@Login,"Enter Email ID! ", LENGTH_SHORT).show()
                 }

                 TextUtils.isEmpty(pass)-> {
                     Toast.makeText(this@Login, "Enter Password! ", LENGTH_SHORT).show()
                 }

                 else->{
                     FirebaseAuth.getInstance().signInWithEmailAndPassword(emailID,pass).addOnCompleteListener{ task->

                         if(task.isSuccessful){

                             //Store user id in a variable to pass on main activity:
                             val firebaseUser: FirebaseUser = task.result!!.user!!

                             Toast.makeText(this@Login,"Logged In Successfully" ,LENGTH_SHORT).show()

                             val intent = Intent(this@Login,MainActivity::class.java)
                             intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                             intent.putExtra("user_id",firebaseUser)
                             intent.putExtra("email_id",emailID)
                             startActivity(intent)
                             finish()


                         }else{
                             Toast.makeText(this@Login,task.exception!!.message.toString(),
                                 LENGTH_SHORT).show()
                         }
                     }
                 }
             }


        }

    }



}