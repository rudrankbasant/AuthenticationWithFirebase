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

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginText: TextView = findViewById(R.id.loginTextView)
        val registerButton: Button = findViewById(R.id.buttonRegister)
        val registerEmail: EditText = findViewById(R.id.editTextRegisterEmail)
        val registerPassword: EditText = findViewById(R.id.editTextPassword)
        val confirmPassword: EditText = findViewById(R.id.editTextConfirmPassword)

        loginText.setOnClickListener{
            startActivity(Intent(this@Register,Login::class.java))
        }

        registerButton.setOnClickListener{
            val emailID:String=registerEmail.text.toString().trim{it <=' '}
            val pass:String=registerPassword.text.toString().trim{it <=' '}
            val confirmPass:String=confirmPassword.text.toString().trim{it <=' '}

            when{
                TextUtils.isEmpty(emailID)->{
                    Toast.makeText(this@Register,"Enter Email ID! ",LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(pass)->{
                    Toast.makeText(this@Register,"Enter Password! ",LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(confirmPass)->{
                    Toast.makeText(this@Register,"Confirm Password! ",LENGTH_SHORT).show()
                }

                pass!=confirmPass->{
                    Toast.makeText(this@Register,"Passwords Do Not Match! ",LENGTH_SHORT).show()

                }

                else->{
                    //Create an instance and register a user with email and password:
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailID,pass).addOnCompleteListener { task ->
                        //if registration is successful:
                        if (task.isSuccessful) {

                            Toast.makeText(
                                this@Register, "Registered Successfully.",
                                LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@Register,Login::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("email_id", emailID)
                            startActivity(intent)
                            finish()

                        } else {
                            //If registration not successful:
                            Toast.makeText(this@Register,task.exception!!.message.toString(),
                                LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }
}