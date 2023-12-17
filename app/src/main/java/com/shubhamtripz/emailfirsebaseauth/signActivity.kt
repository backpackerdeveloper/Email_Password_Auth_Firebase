package com.shubhamtripz.emailfirsebaseauth

import android.content.EntityIterator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

            // firebase initialize
        firebaseAuth = FirebaseAuth.getInstance()

        // user check
        if (firebaseAuth.currentUser != null){
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val loginpgbtn = findViewById<TextView>(R.id.loginpagebtn)
        loginpgbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btnlogin = findViewById<Button>(R.id.signbtn)

        btnlogin.setOnClickListener {
            signup()
        }

    }


    private fun signup(){
        val etEmailAddress = findViewById<EditText>(R.id.emailet)
        val etpassword = findViewById<EditText>(R.id.passet)
        val etconfirmpassword = findViewById<EditText>(R.id.confrimpasset)

        val email = etEmailAddress.text.toString()
        val password = etpassword.text.toString()
        val confirmpassword = etconfirmpassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmpassword.isBlank()){
            Toast.makeText(this, "Email and pass cant be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmpassword){
            Toast.makeText(this, "Password and confirm password not matched", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
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