package com.shubhamtripz.emailfirsebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // firebase initialize
        firebaseAuth = FirebaseAuth.getInstance()

        val loginbtn  = findViewById<Button>(R.id.loginbtn)

        loginbtn.setOnClickListener {
            login()

        }

    }

    private fun login(){

        val etEmailAddress = findViewById<EditText>(R.id.emailloginet)
        val etpassword = findViewById<EditText>(R.id.pass_login_et)

        val email = etEmailAddress.text.toString()
        val password = etpassword.text.toString()

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Password and confirm cant be empty", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            else{
                Toast.makeText(this, "error sign", Toast.LENGTH_SHORT).show()
            }
        }

    }


}