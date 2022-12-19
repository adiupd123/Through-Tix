package com.adiupd123.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), OnClickListener{

    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var emailEDT: EditText
    private lateinit var passEDT: EditText
    private lateinit var sigInBtn: Button
    private lateinit var forgotBtn: Button
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        emailEDT = findViewById(R.id.email_editText)
        passEDT = findViewById(R.id.password_editText)

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("key", 10)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}